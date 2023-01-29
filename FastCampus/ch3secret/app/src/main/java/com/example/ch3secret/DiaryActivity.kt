package com.example.ch3secret

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.ch3secret.databinding.ActivityDiaryBinding

class DiaryActivity : AppCompatActivity() {

    // 메인스레드에 연결된 핸들러
    // 핸들러 -> 기본 스레드 통신 방법
    private val handler = Handler(Looper.getMainLooper())

    private lateinit var binding: ActivityDiaryBinding

    private val diaryEditText: EditText by lazy {
        binding.diaryEditText
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_diary)

        // diary로 되어있는 정보를 가져옴
        val detailPreferences = getSharedPreferences("diary", Context.MODE_PRIVATE)

        diaryEditText.setText(detailPreferences.getString("detail", ""))


        // 스레드 사용
        // 메인스레드 (UI 스레드 )
        // 새로운 스레드 -> 기타작업
        val runnable = Runnable {
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit {
                // 비동기로 넘긴다?
                // commit을 true로 주면 UI를 기다림 -> 동기로 실행
                // false 백그라운드에서 수시로 저장을 계속함 -> 비동기 (실패 알람을 받지 않음)

                putString("detail", diaryEditText.text.toString())
            }

            Log.d("Diary", "SAVE!!! :: ${diaryEditText.text.toString()}")
        }

        diaryEditText.addTextChangedListener {

            Log.d("Diary", "TextChanged :: $it")
            // 이전의 run이 있다면 삭제한다.
            handler.removeCallbacks(runnable)
            // 일정 시간 이후 실행
            handler.postDelayed(runnable, 500)
        }
    }
}
