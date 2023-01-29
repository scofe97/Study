package com.example.ch3secret

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import com.example.ch3secret.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val numberPicker1: NumberPicker by lazy {
        binding.numberPicker1.apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val numberPicker2: NumberPicker by lazy {
        binding.numberPicker2.apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val numberPicker3: NumberPicker by lazy {
        binding.numberPicker3.apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val openButton: AppCompatButton by lazy {
        binding.openButton
    }

    private val changePasswordButton: AppCompatButton by lazy {
        binding.changePasswordButton
    }

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 뷰가 다그려짐을 알려주는 시점이 onCreate이므로 lazy를 초기화해줌
        // lazy는 사용하는 시점에 초기화 해줌
        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {

            // 파일이름, 모드
            // SharedPreferences -> Preference라는 파일을 공유할수 있도록 함
            // 이번에는 이 파일에만 사용하므로 PRIVATE 사용
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (changePasswordMode) {
                Toast.makeText(this, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 패스워드 저장된 값을 가져옴
            // 키-값 형식으로 가져옴
            // 디폴트 숫자는 빈값을 방지하기위해 넣어줌
            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                // 성공
                startActivity(Intent(this, DiaryActivity::class.java))
            } else {
                shwoErrorAlertDialog()
            }
        }

        changePasswordButton.setOnClickListener {

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            // 누르면 다른작업 못하도록 설정
            if (changePasswordMode) {
                // edit 함수를 쉽게 사용하도록 만듬
                passwordPreferences.edit(true) {
                    val passwordFromUser =
                        "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
                    putString("password", passwordFromUser)

                    // commit , apply -> 데이터 사용
                    // commit -> 다 적용될때까지 기다림
                    // apply -> 바로 적용
                }

                changePasswordMode = false
                changePasswordButton.setBackgroundColor(Color.BLACK)

            } else {
                // changePasswordMode 활성화 :: 비밀번호가 맞는지 체크
                if (changePasswordMode) {
                    Toast.makeText(this, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // 패스워드 저장된 값을 가져옴
                // 키-값 형식으로 가져옴
                // 디폴트 숫자는 빈값을 방지하기위해 넣어줌
                if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()

                    changePasswordButton.setBackgroundColor(Color.RED)

                } else {
                    shwoErrorAlertDialog()
                }

            }

        }
    }

    private fun shwoErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("실패")
            .setMessage("비밀번호가 잘못되었습니다")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()
    }
}