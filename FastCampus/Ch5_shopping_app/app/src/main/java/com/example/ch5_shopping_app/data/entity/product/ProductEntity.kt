package com.example.ch5_shopping_app.data.entity.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class ProductEntity(
        @PrimaryKey val id : Long,
        val createAt : Date,
        val productName : String,
        val productPrice : Int,
        val productImage : String,
        val productType : String,
        val productIntroductionImage : String
)
