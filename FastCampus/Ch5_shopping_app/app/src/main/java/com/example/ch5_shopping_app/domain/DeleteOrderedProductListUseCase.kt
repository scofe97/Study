package com.example.ch5_shopping_app.domain

import com.example.ch5_shopping_app.data.repository.ProductRepository

internal class DeleteOrderedProductListUseCase(
    private val productRepository: ProductRepository
) : UseCase {

    suspend operator fun invoke() {
        return productRepository.deleteAll()
    }
}