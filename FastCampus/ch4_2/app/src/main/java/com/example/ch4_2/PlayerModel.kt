package com.example.ch4_2

data class PlayerModel(
    private val playMusicList: List<MusicModel> = emptyList(),
    var currentPosition : Int =  -1,
    var isWatchingPlayListView : Boolean = true
) {

    // 데이터를 꺼내갈때마다 실제로 position 값을 보고 재생중인지 아닌지 업데이트 해줌
    fun getAdapterModels() : List<MusicModel>{
        return playMusicList.mapIndexed{ index, musicModel ->
            // 인덱스 == currentPosition 이 같으면 재생중이라 판단

            // copy -> 클래스를 새로 만들어줌
            // 어댑터에서 초기화 된건 갱신을 못시킴 (old new 둘다 초기화 되어서)
            val newItem =  musicModel.copy(
                isPlaying = index == currentPosition
            )
            newItem
        }
    }

    fun updateCurrentPosition(musicModel: MusicModel){
        currentPosition = playMusicList.indexOf(musicModel)
    }

    fun nextMusic() : MusicModel?{
        if(playMusicList.isEmpty()) return null

        currentPosition = if((currentPosition + 1 ) == playMusicList.size) 0 else currentPosition + 1
        return playMusicList[currentPosition]
    }

    fun previousMusic() : MusicModel?{
        if(playMusicList.isEmpty()) return null

        currentPosition = if((currentPosition - 1 ) < 0 ) playMusicList.lastIndex else currentPosition - 1
        return playMusicList[currentPosition]
    }
    fun currentMusicModel() : MusicModel?{
        if(playMusicList.isEmpty()) return null

        return playMusicList[currentPosition]
    }
}