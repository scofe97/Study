package com.example.ch4_1

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch4_1.adapter.VideoAdapter
import com.example.ch4_1.databinding.FragmentPlayerBinding
import com.example.ch4_1.model.VideoDto
import com.example.ch4_1.service.VideoService
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.abs

class PlayerFragment : Fragment(R.layout.fragment_player) {

    private var binding : FragmentPlayerBinding? = null

    private lateinit var videoAdapter : VideoAdapter
    private var player : SimpleExoPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentPlayerBinding = FragmentPlayerBinding.bind(view)
        binding = fragmentPlayerBinding

        getVideoList()
        initMotionLayout(fragmentPlayerBinding)
        initRecyclerView(fragmentPlayerBinding)
        initPlayer(fragmentPlayerBinding)
        initControlButton(fragmentPlayerBinding)

    }

    // 백그라운드로 내려갈때 멈추게 함
    override fun onStop() {
        super.onStop()

        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
        player?.release()
    }

    private fun initMotionLayout(fragmentPlayerBinding : FragmentPlayerBinding){
        fragmentPlayerBinding.playerMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                // 시작
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                // 변화중
                binding?.let{
                    // 프래그먼트는 액티비티가 무조건 존재하지만 어떤건지는 모름
                    (activity as MainActivity).also{ mainActivity ->
                        // 이제 프래그먼트의 이벤트와 진행정도를 공유하므로 같이 실행됨
                        mainActivity.findViewById<MotionLayout>(R.id.mainMotionLayout).progress = abs(progress)
                    }
                }
            }
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                // 완료되었을 때
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }

        })
    }

    private fun initRecyclerView(fragmentPlayerBinding: FragmentPlayerBinding){

        videoAdapter = VideoAdapter(callback = { url, title ->
            play(url, title)
        })

        fragmentPlayerBinding.fragmentRecyclerView.apply{
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
    private fun initPlayer(fragmentPlayerBinding: FragmentPlayerBinding){

        // 플레이어 생성
       context?.let {
           player = SimpleExoPlayer.Builder(it).build()
       }

        fragmentPlayerBinding.playerView.player = player

        binding?.let{
            player?.addListener(object: Player.EventListener{
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    super.onIsPlayingChanged(isPlaying)
                    // 플레이 여부가 바뀔떄마다 실행됨
                    // 실행되면 true 아니면 false

                    if (isPlaying){
                        it.bottomPlayerControlButton.setImageResource(R.drawable.ic_baseline_pause_24)
                    }else{
                        it.bottomPlayerControlButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    }
                }
            })
        }
    }

    private fun initControlButton(fragmentPlayerBinding: FragmentPlayerBinding){
        fragmentPlayerBinding.bottomPlayerControlButton.setOnClickListener{
            val player = this.player ?: return@setOnClickListener

            if(player.isPlaying){
                player.pause()
            }else{
                player.play()
            }
        }
    }

    private fun getVideoList(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(VideoService::class.java).also { it ->
            it.listVideos()
                .enqueue(object : Callback<VideoDto> {
                    override fun onResponse(call: Call<VideoDto>, response: Response<VideoDto>) {
                        if(!response.isSuccessful){
                            Log.d("MainActivity", "response fail")
                            return
                        }

                        response.body()?.let{ videoDto ->
                            videoDto.videos.forEach{ video ->
                                Log.d("MainActivity", video.toString())
                            }

                            videoAdapter.submitList(videoDto.videos)
                        }
                    }

                    override fun onFailure(call: Call<VideoDto>, t: Throwable) {
                        // 예외처리
                    }
                })
        }
    }

    fun play(url : String, title : String){

        context?.let{
            val dataSourceFactory = DefaultDataSourceFactory(it)

            // URL 을 가져오기 위한 과정
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
            player?.setMediaSource(mediaSource)
            player?.prepare()
            player?.play()
        }

        binding?.let{
            it.playerMotionLayout.transitionToEnd()
            it.bottomTitleTextView.text = title
        }
    }

}