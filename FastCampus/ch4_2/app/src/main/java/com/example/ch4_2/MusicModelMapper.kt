package com.example.ch4_2

import com.example.ch4_2.service.MusicDto
import com.example.ch4_2.service.MusicEntity

fun MusicEntity.mapper(id : Long) : MusicModel =
    MusicModel(
        id = id,
        streamUrl = streamUrl,
        coverUrl = coverUrl,
        track = track,
        artist = artist
    )

fun MusicDto.mapper(): PlayerModel =
    PlayerModel(
        playMusicList = musics.mapIndexed() { index, musicEntity ->
            musicEntity.mapper(index.toLong())
        }
    )