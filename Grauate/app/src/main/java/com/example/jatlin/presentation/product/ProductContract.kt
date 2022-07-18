package com.example.jatlin.presentation.product

import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.presentation.BasePresenter
import com.example.jatlin.presentation.BaseView


interface ProductContract {

    interface View : BaseView<Presenter>{

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showDescription(message: String)

        fun showLog(message: String)

        fun setAdapter(productList : List<ProductEntity>)

        fun showProductList()

        fun showEmptyList()

        fun navigateCreateFragment()

        fun navigateDetailProduct(productItem : ProductEntity)
    }

    interface Presenter : BasePresenter{

    }
}