package com.example.myrescyleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 가로나 세로 방향으로 스크롤이 가능
        // 레이아웃 매니저
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyleView.layoutManager = layoutManager

        val adapter = PersonAdapter()
        adapter.items.add(Person("홍길동1", "010-1000-1000"))
        adapter.items.add(Person("홍길동2", "020-1000-1000"))
        adapter.items.add(Person("홍길동3", "030-1000-1000"))
        adapter.items.add(Person("홍길동4", "040-1000-1000"))
        adapter.items.add(Person("홍길동5", "050-1000-1000"))
        adapter.items.add(Person("홍길동6", "060-1000-1000"))

        recyleView.adapter = adapter

        adapter.listener = object: OnPersonItemClickListener{
            override fun onItemClick(holder: PersonAdapter.ViewHolder?, view: View?, position: Int) {
                showToast("아이템 클릭됨 : ${position}")
            }
        }
    }
    fun showToast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}