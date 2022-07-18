package com.example.jatlin.presentation.product.detail

import android.util.Log
import com.example.jatlin.DBKey
import com.example.jatlin.DBKey.Companion.CHILD_CHAT
import com.example.jatlin.DBKey.Companion.DB_CHATS
import com.example.jatlin.data.entity.ChatRoomEntity
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductDetailPresenter(
    private val view: ProductDetailContract.View
) : ProductDetailContract.Presenter {

    private val userDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DBKey.DB_USERS)
    }
    private val productDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DBKey.DB_PRODUCT)
    }
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private var sellerUser: UserEntity? = UserEntity()

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchData()
    }

    override fun onDestroyView() {

    }

    private fun fetchData() {

    }

    override fun getFirebaseDB(sellerId: String) {
        scope.launch {
            try {
                view.showLoadingIndicator()
                userDB.child(sellerId).get().addOnSuccessListener {
                    Log.d("firebase", "get : ${it.getValue(UserEntity::class.java)}")
                    sellerUser = it.getValue(UserEntity::class.java)
                    view.showLog("${sellerUser}")
                    view.sellerProfile(sellerUser)

                }.addOnFailureListener {
                    Log.e("firebase", "Error getting data", it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                view.showDescription("파이어베이스 정보가져오기 실패")
            } finally {
                view.hideLoadingIndicator()
            }

        }
    }

    override fun addChatFirebaseDB(product: ProductEntity) {
        scope.launch {
            try {
                view.showLoadingIndicator()

                userDB.child(product.sellerId).get().addOnSuccessListener {
                    Log.d("firebase", "get : ${it.getValue(UserEntity::class.java)}")
                    sellerUser = it.getValue(UserEntity::class.java)

                    if (sellerUser?.userUid != auth.currentUser?.uid) {

                        var chatRoom = ChatRoomEntity(
                            product.productName,
                            sellerUser!!.userUid,
                            sellerUser!!.userImage,
                            auth.currentUser!!.uid,
                            auth.currentUser!!.photoUrl?.toString(),
                            System.currentTimeMillis()
                        )
                        view.showLog("$chatRoom")

                        val chatValues = chatRoom.toMap()
                        val chatUpdates = hashMapOf<String, Any>(
                            "/${auth.currentUser!!.uid}/${CHILD_CHAT}/${sellerUser!!.userUid}" to chatValues,
                            "/${sellerUser!!.userUid}/${CHILD_CHAT}/${auth.currentUser!!.uid}" to chatValues,
                        )
                        userDB.updateChildren(chatUpdates)
                        view.navigateChatFragment()

                    } else {
                        view.showDescription("내가 올린 제품입니다.")
                    }

                }.addOnFailureListener {
                    Log.e("firebase", "Error getting data", it)
                }

            } catch (e: Exception) {
                view.showLog("채팅방 생성 실패")
            } finally {
                view.hideLoadingIndicator()
            }


        }
    }

    override fun removeProductFirebaseDB() {
        val productItem = view.getProductItem()
        productDB.child(productItem.createdAt.toString()).removeValue()
    }

}