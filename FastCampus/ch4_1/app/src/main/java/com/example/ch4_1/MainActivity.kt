package com.example.ch4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch4_1.adapter.VideoAdapter
import com.example.ch4_1.databinding.ActivityMainBinding
import com.example.ch4_1.model.VideoDto
import com.example.ch4_1.service.VideoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var videoAdapter : VideoAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 시발련아 이거머냐고 ㅋㅋㅋㅋㅋ
        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment())
            .commit()

        videoAdapter = VideoAdapter(callback = { url, title ->
            // PlayFragment 에서 함수 가져오는법
            supportFragmentManager.fragments.find{it is PlayerFragment}?.let{
                (it as PlayerFragment).play(url, title)
            }
        })

        findViewById<RecyclerView>(R.id.mainRecyclerView).apply{
            adapter = videoAdapter
            layoutManager = LinearLayoutManager(context)
        }


        getVideoList()
    }

    private fun getVideoList(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(VideoService::class.java).also { it ->
            it.listVideos()
                .enqueue(object : Callback<VideoDto>{
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
}