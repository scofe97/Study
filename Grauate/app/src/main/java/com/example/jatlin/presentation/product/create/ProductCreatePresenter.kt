package com.example.jatlin.presentation.product.create

import android.net.Uri
import android.util.Log
import com.example.jatlin.DBKey.Companion.DB_PRODUCT
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.presentation.product.ProductContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductCreatePresenter(
    private val view : ProductCreateContract.View,
) : ProductCreateContract.Presenter {

    companion object {
        const val TAG = "ProductCreatePresenter"
    }

    private val storage: FirebaseStorage by lazy {
        Firebase.storage
    }

    private val productDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DB_PRODUCT)
    }
    
    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchData()
    }

    override fun onDestroyView() {

    }

    private fun fetchData(){

    }

    override fun uploadPhoto(uri: Uri, successHandler: (String) -> Unit, errorHandler: () -> Unit) {
        scope.launch{
            try{
                view.showLoadingIndicator()
                Log.d(TAG,"uploadPhoto 시작")
                val fileName = "${System.currentTimeMillis()}.png"
                storage.reference.child("product/photo").child(fileName)
                    .putFile(uri)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            storage.reference.child("product/photo").child(fileName)
                                .downloadUrl
                                .addOnSuccessListener { uri ->
                                    successHandler(uri.toString())
                                    view.hideLoadingIndicator()
                                }.addOnFailureListener {
                                    errorHandler()
                                    view.hideLoadingIndicator()
                                }
                        } else {
                            errorHandler()
                        }
                    }
            }catch (e : Exception){
                view.showDescription("이미지를 불러오지 못했습니다.")
                view.showLog("uploadPhoto 실패")
            }finally {
                view.showLog("uploadPhoto 종료")
            }
        }
    }

    override fun insertFirebaseDB(sellerId: String, title: String, price: Int, description: String, imageUrl: String) {
        scope.launch {
            try{
                view.showLoadingIndicator()

                val productItem = view.getProductItem()
                if(productItem != null){
                    val model = ProductEntity(productItem.createdAt,System.currentTimeMillis(),sellerId, title,price,imageUrl,description,null)
                    productDB.child(productItem.createdAt.toString()).setValue(model).addOnSuccessListener {
                        view.hideLoadingIndicator()
                        view.showLog("파이어베이스 상품업데이트 성공")
                    }.addOnFailureListener {
                        view.hideLoadingIndicator()
                        view.showLog("파이어베이스 상품업데이트에 실패함")
                    }
                }else{
                    val model = ProductEntity(System.currentTimeMillis(),System.currentTimeMillis(),sellerId, title,price,imageUrl,description,null)

                    productDB.child(model.createdAt.toString()).setValue(model).addOnSuccessListener {
                        view.hideLoadingIndicator()
                        view.showLog("파이어베이스 상품저장 성공")
                    }.addOnFailureListener {
                        view.hideLoadingIndicator()
                        view.showLog("파이어베이스 상품저장에 실패함")
                    }
                }
            }catch (e : Exception){
                view.showLog("파이어베이스 상품저장에 실패함")
            }finally {
                view.showLog("파이어베이스 상품저장 종료")
            }
        }
    }
}