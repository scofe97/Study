package com.example.ch3_1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.ch3_1.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val resultTextView: TextView by lazy {
        binding.resultTextView
    }

    private val firebaseToken: TextView by lazy {
        binding.firebaseTokenTextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initFirebase()
        updateResult()
    }

    override fun onNewIntent(intent: Intent?) {
        // FLAG_ACTIVITY_SINGLE_TOP 에서 상단이 같은경우 실행됨
        super.onNewIntent(intent)

        setIntent(intent)
        updateResult(true)
    }

    // 토큰 가져오기기
    private fun initFirebase() {
        FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task ->
                    // task 가 성공적이면
                    if (task.isSuccessful) {
                        firebaseToken.text = task.result
                    }
                }
    }

    @SuppressLint("SetTextI18n")
    private fun updateResult(isNewIntent : Boolean = false){
        resultTextView.text = (intent.getStringExtra("notificationType") ?: "앱 런쳐") +
            if(isNewIntent){
                "(으)로 갱신했습니다"
            }else{
                "(으)로 실행했습니다."
            }
    }

}