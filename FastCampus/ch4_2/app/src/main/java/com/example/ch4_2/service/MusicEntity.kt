package com.example.ch4_2.service

import com.example.ch4_2.PlayerModel
import com.example.ch4_2.mapper
import com.google.android.exoplayer2.Player
import com.google.gson.annotations.SerializedName

data class MusicEntity(
    @SerializedName("track") val track: String,
    @SerializedName("streamUrl") val streamUrl: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("coverUrl") val coverUrl: String
) {

}
