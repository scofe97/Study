package com.example.myimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var startX = 0.0f
    var startY = 0.0f

    var leftMargin = 0
    var topMargin = 0

    var carWidth = 0.0f
    var carHeight = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carWidth = resources.getDimension(R.dimen.car_width)
        carHeight = resources.getDimension(R.dimen.car_height)

        // v는 터치한 객체, event는 motionEvent 생성 (어디를 눌렸는지 위치파악)
        carImageView.setOnTouchListener{v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN ->{
                    println("손가락 눌림")

                    startX = event.x
                    startY = event.y
                }
                MotionEvent.ACTION_MOVE ->{
                    println("손가락 움직임")

                    val diffX = event.x - startX
                    val diffY = event.y - startY

                    leftMargin += diffX.toInt()
                    topMargin += diffY.toInt()

                    // carImageView.marginTop 설정은 못하고 읽기만 가능

                    val layOutParams = FrameLayout.LayoutParams(
                            carWidth.toInt(),
                            carHeight.toInt()
                        // 가로크기 세로크기를 설정해줌
                        // 여기서의 단위는 픽셀 (바꾸려면 리소스에서 변경해야함)
                    )
                    layOutParams.leftMargin = leftMargin
                    layOutParams.topMargin = topMargin

                    carImageView.layoutParams = layOutParams
                }
                MotionEvent.ACTION_UP ->{
                    println("손가락 뗌")
                }
            }
            // 람다에서는 단순히 리턴을 쓸수는 없음
            return@setOnTouchListener true
        }
    }
}