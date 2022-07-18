package com.example.jatlin.presentation.profile

import com.example.jatlin.presentation.BasePresenter
import com.example.jatlin.presentation.BaseView
import com.example.jatlin.data.entity.UserEntity

interface ProfileContract {

    // 사용자 액션을 Presenter 에 떠넘기고 UI 업데이트만 하는 클래스
    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showLoginState()

        fun showLoginUser(model : UserEntity?)

        fun showLogoutState()

        fun showEditUserState()

        fun showDescription(message: String)

        fun showLog(message: String)

        fun navigateLoginFragment()
    }

    // 사용자의 액션을 받아 로직을 처리하고, 모델에 데이터 변경 또는 ui 업데이트 로직
    interface Presenter : BasePresenter{

        val userModel: UserEntity

        fun logout()

        fun updateFirebaseDB(name : String, phone:String)

    }
}