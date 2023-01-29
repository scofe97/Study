package com.example.ch5_shopping_app.data.repository

import com.example.ch5_shopping_app.data.db.dao.ProductDao
import com.example.ch5_shopping_app.data.entity.product.ProductEntity
import com.example.ch5_shopping_app.data.network.ProductApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultProductRepository(
    private val productApi : ProductApiService,
    private val ioDispatcher : CoroutineDispatcher,
    private val productDao: ProductDao
) : ProductRepository{
    override suspend fun getProductList(): List<ProductEntity> = withContext(ioDispatcher) {
        val response = productApi.getProducts()

        // withContext 가 끝나기전까지 해당 코루틴이 일시정지됨됨
       return@withContext if(response.isSuccessful){
            response.body()?.items?.map {it.toEntity() } ?: listOf()
        } else{
            listOf()
        }
    }

    override suspend fun getLocalProductList(): List<ProductEntity> = withContext(ioDispatcher) {
        productDao.getAll()
    }

    override suspend fun insertProductItem(productItem: ProductEntity): Long = withContext(ioDispatcher) {
        productDao.insert(productItem)
    }

    override suspend fun insertProductList(ProductList: List<ProductEntity>) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProductItem(ProductItem: ProductEntity) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }

    override suspend fun getProductItem(itemId: Long): ProductEntity? = withContext(ioDispatcher) {
        val response = productApi.getProduct(itemId)

        // withContext 가 끝나기전까지 해당 코루틴이 일시정지됨됨
        return@withContext if(response.isSuccessful){
            response.body()?.toEntity()
        } else{
            null
        }
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        productDao.deleteAll()
    }

    override suspend fun deleteProductItem(id: Long) = withContext(ioDispatcher) {
        TODO("Not yet implemented")
    }
}