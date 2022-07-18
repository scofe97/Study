package com.example.jatlin.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    val createdAt: Long,
    val updatedAt: Long,

    val sellerId: String,
    val productName: String,
    val productPrice : Int,
    val productImage : String,
    val productIntroduction : String,
    val productType: String?,
): Parcelable{
    constructor(): this(0L,0L, "","", 0, "", "", "")
}
