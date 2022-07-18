package com.example.ch5_shopping_app.presentation.list

import com.example.ch5_shopping_app.data.entity.product.ProductEntity

// Sealed Class
// 값이 제한된 집합의 유형 중 하나를 가질 수 있지만 다른 유형을 가질 수 없는 제한된
// 클래스 계층 구조를 나타내는데 사용
sealed class ProductListState {

    object UnInitialized : ProductListState()

    object Loading : ProductListState()

    data class Success(
            val productList : List<ProductEntity>
    ) : ProductListState()

    object Error : ProductListState()
}