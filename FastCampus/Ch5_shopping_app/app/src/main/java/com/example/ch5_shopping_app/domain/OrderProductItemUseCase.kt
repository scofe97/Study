package com.example.ch5_shopping_app.domain

import com.example.ch5_shopping_app.data.entity.product.ProductEntity
import com.example.ch5_shopping_app.data.repository.ProductRepository

internal class OrderProductItemUseCase(
    private val productRepository: ProductRepository
) : UseCase {

    suspend operator fun invoke(productEntity: ProductEntity) : Long {
        return productRepository.insertProductItem(productEntity)
    }
}