package com.example.myfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var loginFragment:LoginFragment = LoginFragment()
    var menuFragment:MenuFragment = MenuFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLoginButton.setOnClickListener {
            // 프래그먼트 매니저에게 트랜잭션을 하나 만들어달라 요청 (트랜잭션 : 여러 명령을 한번에 실행하게할수있는)
            // commit 는 함수를 한번에 실행
            //supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment()).commit()

            /*with(supportFragmentManager.beginTransaction()){
                replace(R.id.container, LoginFragment())
            }.commit()*/

            onFragmentChanged(0)
        }

        showMenuButton.setOnClickListener {
            // 프래그먼트 매니저에게 트랜잭션을 하나 만들어달라 요청 (트랜잭션 : 여러 명령을 한번에 실행하게할수있는)
            // commit 는 함수를 한번에 실행
            // supportFragmentManager.beginTransaction().replace(R.id.container, MenuFragment()).commit()

            /*with(supportFragmentManager.beginTransaction()){
                replace(R.id.container, MenuFragment())
            }.commit()*/

            onFragmentChanged(1)
        }
    }

    fun onFragmentChanged(index:Int){
        when(index){
            0 -> {
                with(supportFragmentManager.beginTransaction()){
                    replace(R.id.container, LoginFragment())
                }.commit()
            }
            1 ->{
                with(supportFragmentManager.beginTransaction()){
                    replace(R.id.container, MenuFragment())
                }.commit()
            }
        }
    }
}