package com.example.jatlin.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.Exclude

@Entity
data class UserEntity(
    @PrimaryKey val userUid : String,
    val userEmail : String,
    val userName : String?,
    val userPhoneNumber : String?,
    val userImage : String?
){
    constructor(): this("", "이메일 없음","익명", null, null)

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "userUid" to userUid,
            "userEmail" to userEmail,
            "userName" to userName,
            "userPhoneNumber" to userPhoneNumber,
            "userImage" to userImage
        )
    }
}