package com.example.jatlin.presentation

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT
}