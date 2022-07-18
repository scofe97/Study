package com.example.ch5_shopping_app.data.response

import com.example.ch5_shopping_app.data.entity.product.ProductEntity
import java.util.*

data class ProductResponse(
    val id : Long,
    val createAt : Long,
    val productName : String,
    val productPrice : Int,
    val productImage : String,
    val productType : String,
    val productIntroductionImage : String
){

    fun toEntity(): ProductEntity =
        ProductEntity(
            id = id.toLong(),
            createAt = Date(createAt),
            productName = productName,
            productPrice = productPrice.toDouble().toInt(),
            productImage = productImage,
            productType = productType,
            productIntroductionImage = productIntroductionImage
        )
}
