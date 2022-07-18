package com.example.jatlin.presentation.login

import com.example.jatlin.DBKey
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.domain.GetCurrentUserUseCase
import com.example.jatlin.domain.InsertUserUseCase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LoginPresenter(
    private val view: LoginContract.View,
    private val insetUser: InsertUserUseCase
) : LoginContract.Presenter {

    companion object {
        const val TAG = "LoginPresenter"
    }

    override val scope: CoroutineScope = MainScope()

    override fun onViewCreated() {
        fetchData()
    }

    override fun onDestroyView() {}

    private fun fetchData() = scope.launch {
        try {
            view.showLoadingIndicator()
        } catch (e: Exception) {
            e.printStackTrace()
            view.showLog("로그인 과정에서 오류가 생김")
        } finally {
            view.hideLoadingIndicator()
        }
    }

    override fun createUserDB(user: FirebaseUser) {
        scope.launch {
            try{
                view.showLoadingIndicator()
                val userModel = UserEntity(user.uid, user.email?: "이메일 없음", user.displayName, user.phoneNumber, user.photoUrl?.toString())
                insetUser(userModel)
                view.showLog("파이어베이스 유저저장 성공")
            }catch (e: Exception) {
                e.printStackTrace()
                view.showLog("파이어베이스 유저저장 과정에서 오류가 생김")
            } finally {
                view.hideLoadingIndicator()
            }
        }
    }

}