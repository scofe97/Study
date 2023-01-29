package com.example.mycustomlayout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class Part1 : LinearLayout {

    // ui객체는 생성자를 무조건 담아야한다고 강제함
    constructor(context: Context?) : super(context){
        onInit(context) // 이 함수가 호출되면 xml파일도 부름
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        onInit(context) // 이 함수가 호출되면 xml파일도 부름
    }

    fun onInit(context: Context?){
        LayoutInflater.from(context).inflate(R.layout.part1,this,true)
    }
}