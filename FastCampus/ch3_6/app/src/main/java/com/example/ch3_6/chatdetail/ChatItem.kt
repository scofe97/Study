package com.example.ch3_6.chatdetail

data class ChatItem(
    val sendId : String,
    val message : String
) {
    constructor() : this("","")
}