package com.example.ch4_2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ch4_2.databinding.FragmentPlayerBinding
import com.example.ch4_2.service.MusicDto
import com.example.ch4_2.service.MusicService
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PlayerFragment : Fragment(R.layout.fragment_player) {

    private var model : PlayerModel = PlayerModel()
    private var binding : FragmentPlayerBinding? = null
    private var player : SimpleExoPlayer? = null
    private lateinit var playListAdapter: PlayListAdapter

    private val updateSeekRunnable = Runnable {
        updateSeek()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentPlayerBinding = FragmentPlayerBinding.bind(view)
        binding = fragmentPlayerBinding
        getVideoListFromServer()

        initPlayView(fragmentPlayerBinding)
        initPlayListButton(fragmentPlayerBinding)
        initPlayControlButtons(fragmentPlayerBinding)
        initRecycleView(fragmentPlayerBinding)
        initSeekBar(fragmentPlayerBinding)

    }
    private fun initSeekBar(fragmentPlayerBinding: FragmentPlayerBinding){
        fragmentPlayerBinding.playerSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                (seekBar?.progress?.times(1000))?.let { player?.seekTo(it.toLong()) }
            }

        })
        fragmentPlayerBinding.playListSeekBar.setOnTouchListener{ v, event ->
            false
        }
    }

    // ExoPlayer 버튼 구현
    private fun initPlayControlButtons(fragmentPlayerBinding: FragmentPlayerBinding) {

        fragmentPlayerBinding.playControlImageView.setOnClickListener{
            val player = this.player ?: return@setOnClickListener

            if(player.isPlaying){
                player.pause()
            }else{
                player.play()
            }
        }

        fragmentPlayerBinding.skipNextImageView.setOnClickListener{
            val nextMusic = model.nextMusic() ?: return@setOnClickListener
            playMusic(nextMusic)
        }

        fragmentPlayerBinding.skipPreviousImageView.setOnClickListener{
            val previousMusic = model.previousMusic() ?: return@setOnClickListener
            playMusic(previousMusic)
        }

    }

    private fun initPlayView(fragmentPlayerBinding: FragmentPlayerBinding) {
        // Exoplayer 생성
        context?.let{
            player = SimpleExoPlayer.Builder(it).build()
        }
        fragmentPlayerBinding.playerView.player = player

        binding?.let{ binding ->
            // play 에 listener 걸기

            player?.addListener(object : Player.EventListener{

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)

                    if(isPlaying){
                        binding.playControlImageView.setImageResource(R.drawable.ic_baseline_pause_24)
                    }else{
                        binding.playControlImageView.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    }
                }

                override fun onPlaybackStateChanged(state: Int) {
                    super.onPlaybackStateChanged(state)

                    // state 값 리턴 (버퍼링, 준비, 끝 -> 플레이의 상태가 변하면 실행됨 )
                    updateSeek()
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)
                    // 플레이 목록이 변경됨

                    val newIndex = mediaItem?.mediaId ?: return
                    model.currentPosition = newIndex.toInt()
                    updatePlayerView(model.currentMusicModel())
                    playListAdapter.submitList(model.getAdapterModels())
                }
            })
        }
    }
    private fun updateSeek(){
        val player = this.player ?: return
        val duration = if(player.duration >= 0 ) player.duration else 0
        val position = player.currentPosition

        // todo ui update

        updateSeekUi(duration,position)

        val state = player.playbackState

        // 플레이어가 재생 실패가 아니거나 끝난게 아니면면
        // 중복 runnable 방지
        view?.removeCallbacks(updateSeekRunnable)
       if(state != Player.STATE_IDLE && state != Player.STATE_ENDED){
           // 1초마다 실행
           view?.postDelayed(updateSeekRunnable,1000)
       }
    }
    private fun updateSeekUi(duration :Long, position : Long){
        binding?.let{ binding ->
            binding.playListSeekBar.max = (duration / 1000).toInt()
            binding.playListSeekBar.progress = (position / 1000).toInt()

            binding.playerSeekBar.max = (duration / 1000).toInt()
            binding.playerSeekBar.progress = (position / 1000).toInt()

            // 0:00
            binding.playTimeTextView.text = String.format("%02d : %02d",
                TimeUnit.MINUTES.convert(position, TimeUnit.MILLISECONDS),
                (position / 1000) % 60
                )
            binding.totalTimeTextView.text = String.format("%02d : %02d",
                TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS),
                (duration / 1000) % 60
            )
        }
    }

    private fun updatePlayerView(currentMusicModel : MusicModel?){
        currentMusicModel ?: return

        // 플레이 음악에 따른 플레이 뷰 구성
        binding?.let{ binding ->
            binding.trackTextView.text = currentMusicModel.track
            binding.artistTextView.text = currentMusicModel.artist
            Glide.with(binding.coverCardImageView.context)
                .load(currentMusicModel.coverUrl)
                .into(binding.coverImageView)
        }
    }
    private fun initRecycleView(fragmentPlayerBinding: FragmentPlayerBinding) {
        playListAdapter = PlayListAdapter {
            // 음악을 재생
            playMusic(it)
        }

        fragmentPlayerBinding.playListRecyclerView.apply{
            adapter = playListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initPlayListButton(fragmentPlayerBinding: FragmentPlayerBinding) {
        fragmentPlayerBinding.playListImageView.setOnClickListener{
            // 만약에 서버에서 데이터가 다 불러오지 않았을 때 잠금
            if(model.currentPosition == -1) return@setOnClickListener

            fragmentPlayerBinding.playerViewGroup.isVisible = model.isWatchingPlayListView
            fragmentPlayerBinding.playListViewGroup.isVisible = model.isWatchingPlayListView.not()

            model.isWatchingPlayListView = !model.isWatchingPlayListView
        }
    }

    private fun getVideoListFromServer(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MusicService::class.java)
            .also{
                it.listMusics()
                    .enqueue(object:Callback<MusicDto>{
                        override fun onResponse(
                            call: Call<MusicDto>,
                            response: Response<MusicDto>
                        ) {
                            if(!response.isSuccessful){
                                Log.d("PlayerFragment", "응답실패")
                                return
                            }
                            Log.d("PlayerFragment", "${response.body()}")

                            response.body()?.let{ musicDto ->

                                model = musicDto.mapper()
                                setMusicList(model.getAdapterModels())
                                playListAdapter.submitList(model.getAdapterModels())
                            }
                        }

                        override fun onFailure(call: Call<MusicDto>, t: Throwable) {
                            Log.d("PlayerFragment", "실패")
                        }

                    })

            }
    }

    // 재생목록 설정
    private fun setMusicList(modelList : List<MusicModel>){
        context?.let{
            player?.addMediaItems(modelList.map{ musicModel ->
                MediaItem.Builder()
                        // 아이디와 태그 지정이 가능
                    .setMediaId(musicModel.id.toString())
                    .setUri(musicModel.streamUrl)
                    .build()
            })

            player?.prepare()

        }
    }

    // 플레이 하는 함수
    private fun playMusic(musicModel : MusicModel){
        // player 는 playList 의 목록을 모두 가지고 있음 -> mediaItem 형식
        // 프래그먼트 -> 모델형식으로 가짐

        // 플레이어의 list 를 다음으로 넘어가게함
        model.updateCurrentPosition(musicModel)
        player?.seekTo(model.currentPosition, 0)
        player?.play()
    }

    override fun onStop() {
        super.onStop()

        player?.pause()
        view?.removeCallbacks(updateSeekRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
        view?.removeCallbacks(updateSeekRunnable)
        player?.release()
    }

    companion object{
        fun newInstance() : PlayerFragment{
            return PlayerFragment()
        }
    }
}