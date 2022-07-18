package com.example.ch2_7

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton

// 커스텀 뷰로써 안드로이 xml에서 수정하려면 조건이 필요
// 컨텍스트와 어트리뷰트 셋이 전달되어야함

class RecordButton(
    context : Context,
    attrs : AttributeSet
) : AppCompatImageButton(context, attrs) {
    // AppCompat 이전버전에 대한 안드로이드 호환성을 유지하게 해주는거임
    // xml 에서는 왜 안쓰냐? -> xml 내부에서 정의되어있는 기능이 프로젝트에 있음


    init{
        setBackgroundResource(R.drawable.shape_oval_button)
    }

    fun updateIconWithState(state : State){
        when(state){

            State.BEFORE_RECORDING ->{
                setImageResource(R.drawable.ic_record)
            }
            State.ON_RECORDING -> {
                setImageResource(R.drawable.ic_stop)
            }
            State.AFTER_RECORDING -> {
                setImageResource(R.drawable.ic_play)
            }
            State.ON_PLAYING -> {
                setImageResource(R.drawable.ic_stop)
            }
        }
    }

}