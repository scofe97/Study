package com.example.jatlin.presentation.profile

import com.example.jatlin.DBKey.Companion.DB_USERS
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.domain.GetCurrentUserUseCase
import com.example.jatlin.domain.InsertUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ProfilePresenter(
    private val view: ProfileContract.View,
) : ProfileContract.Presenter {

    override val scope: CoroutineScope = MainScope()
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    override lateinit var userModel: UserEntity

    private val userDB: DatabaseReference by lazy { Firebase.database.reference.child(DB_USERS) }

    override fun onViewCreated() {
        fetchData()
    }

    override fun onDestroyView() {}

    private fun fetchData() = scope.launch {
        try {
            view.showLoadingIndicator()
            if (auth.currentUser != null) {
                userDB.child(auth.currentUser!!.uid.toString()).get().addOnSuccessListener {
                    val user = it.getValue(UserEntity::class.java)
                    view.showLog("fetchData 실행 : $user")
                    view.showLoginUser(user)
                    view.showLoginState()
                }.addOnFailureListener {
                    view.showLog("fetchData 오류")
                }
            } else {
                view.showLogoutState()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            view.showLog("로그인 과정에서 오류가 생김")
        } finally {
            view.hideLoadingIndicator()
        }
    }

    override fun logout() {
        scope.launch {
            try {
                view.showLoadingIndicator()
                auth.signOut()
                fetchData()
            } catch (e: Exception) {
                e.printStackTrace()
                view.showLog("로그아웃 과정에서 오류 생김")
            } finally {
                view.hideLoadingIndicator()
            }
        }
    }

    override fun updateFirebaseDB(name: String, phone: String) {
        scope.launch {
            try {
                view.showLoginState()
                userDB.child(auth.currentUser!!.uid.toString()).get().addOnSuccessListener {
                    val user = it.getValue(UserEntity::class.java)
                    val userModel =
                        UserEntity(user!!.userUid, user.userEmail, name, phone, user.userImage)
                    insertUser(userModel)
                    fetchData()
                }.addOnFailureListener {
                    view.showLog("파이어베이스 업데이트 오류")
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            view.hideLoadingIndicator()
        }
    }

    private fun insertUser(user: UserEntity) {
        userDB.child(user.userUid).child("userUid").setValue(user.userUid)
        userDB.child(user.userUid).child("userEmail").setValue(user.userEmail)
        userDB.child(user.userUid).child("userName").setValue(user.userName)
        userDB.child(user.userUid).child("userPhoneNumber").setValue(user.userPhoneNumber)
        userDB.child(user.userUid).child("userImage").setValue(user.userImage)
    }
}