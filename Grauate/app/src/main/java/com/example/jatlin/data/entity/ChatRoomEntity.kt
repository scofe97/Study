package com.example.jatlin.data.entity

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatRoomEntity(
    val chatTitle: String,

    val sellerId: String,
    val sellerImage: String?,

    val buyerId: String,
    val buyerImage: String?,

    val createdAt: Long
) : Parcelable {

    constructor() : this("", "", null, "", null, 0L)

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "chatTitle" to chatTitle,
            "sellerId" to sellerId,
            "sellerImage" to sellerImage,
            "buyerId" to buyerId,
            "buyerImage" to buyerImage,
            "createdAt" to createdAt
        )

    }
}