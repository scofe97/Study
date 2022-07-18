package com.example.ch5_shopping_app.presentation.detail

import com.example.ch5_shopping_app.data.entity.product.ProductEntity

sealed class ProductDetailState {

    object UnInitialized : ProductDetailState()

    object Loading : ProductDetailState()

    data class Success(
            val productEntity: ProductEntity
    ) : ProductDetailState()

    object Order : ProductDetailState()

    object Error : ProductDetailState()
}