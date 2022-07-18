package com.anushka.coroutinesdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                // downloadUserData()
                tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }

    private suspend fun downloadUserData() {

        // 쓰레드를 바꾸기 위해 withContext 로 DisPatcher 를 수정함
        // 또한 함수가 suspend 로 바뀌어야함
        for (i in 1..200000) {
            // Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
            // 이대로 실행시키면 에러가 발생
            // 뷰를 생성한 쓰레드에서만 해당 뷰를 수정할 수 있음
            withContext(Dispatchers.Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }


    }
}
