package com.example.myactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showButton.setOnClickListener {
            // :: intent의 엑티비티 지정, 리플렉션과 관련
            // 실행시에 무언가를 찾아내는것임 (자세히 알필요없음)
            val showIntent = Intent(this, MenuActivity::class.java)
            // contetx값 넣기
            // startActivity 화면전환 (정보전달을 할거면 Result도 추가해서 붙여라)

            showIntent.putExtra("mobile","010-1000-1000") // 이름과 값


            startActivityForResult(showIntent,101)
        }

        showToast("onCreate 호출됨")

        saveButton.setOnClickListener {
            val input1 = input1.text.toString()

            val pref = getSharedPreferences("pref", MODE_PRIVATE) //
            pref.edit {
                putString("userName", input1)
                commit()
            }
        }

        loadButton.setOnClickListener{
            val pref = getSharedPreferences("pref", MODE_PRIVATE)
            val userName = pref.getString("userName", "")
            input1.setText(userName)
            // 프로그램을 종료하고 다시 불러와도 제대로 불러와짐
            // 내부적으로 파일로 저장하는 방식
        }

    }

/*    override fun onStart() {
        super.onStart()
        showToast("onStart 호출됨")
    }

    override fun onResume() {
        super.onResume()
        showToast("onResume 호출됨")
    }

    override fun onPause() {
        super.onPause()
        showToast("onPause 호출됨")
    }
    // Paues나 Resume경우 시작하거나 정지되는 경우 무조건 실행됨
    // 이 부분에 강제종료되었을시의 코드를 줄수도 있음음
    override fun onStop() {
        super.onStop()
        showToast("onStop 호출됨")
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("onDestroy 호출됨")
    }*/

    // on으로 시작하는건 보통 콜백함수임
    // 액티비티 종료시점을 판단함
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            101 -> {
                showToast("메뉴 액티비티에서 응답 받았음 : $requestCode, $resultCode")

                if( data != null){
                    processIntent(data)
                }
            }
        }
    }
    fun processIntent(passedIntent: Intent){

        val data = passedIntent.getStringExtra("data")
        if(data != null){
            showToast("메뉴 액티비티로부터 전달받은 데이터 : $data")
        }
    }
    fun showToast(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
    }

}