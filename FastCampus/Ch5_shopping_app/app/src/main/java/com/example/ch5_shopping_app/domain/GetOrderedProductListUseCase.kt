package com.example.ch5_shopping_app.domain

import com.example.ch5_shopping_app.data.entity.product.ProductEntity
import com.example.ch5_shopping_app.data.repository.ProductRepository

internal class GetOrderedProductListUseCase(
    private val productRepository: ProductRepository
) : UseCase {

    suspend operator fun invoke() : List<ProductEntity> {
        return productRepository.getLocalProductList()
    }
}