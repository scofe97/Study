package com.example.ch3_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.ch3_6.chatlist.ChatListFragment
import com.example.ch3_6.databinding.ActivityMainBinding
import com.example.ch3_6.home.HomeFragment
import com.example.ch3_6.mypage.MyPageFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyPage", "시작")
        Log.d("MyPage", "시작2")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val homeFragment = HomeFragment()
        val chatListFragment = ChatListFragment()
        val myPageFragment = MyPageFragment()

        replaceFragment(homeFragment)

        // 클릭하면 해당 프래그먼트를 어태치해줌
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.chatList -> replaceFragment(chatListFragment)
                R.id.myPage -> replaceFragment(myPageFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        // 액티비티에는 서포트프래그매니지먼트가 있음
        // attach 프래그먼트를 관리하는 것
        // 트랜잭션 -> 작업이 시작한다고 알려주는 기능 (commit 까지 이 작업을 수행해라)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}