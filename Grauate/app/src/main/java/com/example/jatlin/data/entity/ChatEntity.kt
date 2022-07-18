package com.example.jatlin.data.entity

data class ChatEntity(
    val userId : String,
    val userName : String?,
    val userImage : String?,
    val chatContents : String,

    val createdAt : Long
){
    constructor() : this("", "익명", null, "", 0L)
}
