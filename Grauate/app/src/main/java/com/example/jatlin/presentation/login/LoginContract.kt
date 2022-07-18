package com.example.jatlin.presentation.login

import com.example.jatlin.presentation.BasePresenter
import com.example.jatlin.presentation.BaseView
import com.example.jatlin.presentation.profile.ProfileContract
import com.google.firebase.auth.FirebaseUser

interface LoginContract {

    interface View : BaseView<Presenter>{

        fun initViews()

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showDescription(message: String)

        fun showLog(message: String)

        fun navigateProfileFragment()
    }

    interface Presenter : BasePresenter{

        fun createUserDB(firebaseUser: FirebaseUser)
    }
}