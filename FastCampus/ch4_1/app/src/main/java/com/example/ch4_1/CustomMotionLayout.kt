package com.example.ch4_1

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout

class CustomMotionLayout(context : Context, attributes: AttributeSet? = null) : MotionLayout(context,attributes) {
    // 터치를 흘려주기 위한 클래스

    // 정확한 곳을 눌려야 동작하게함
    private var motionTouchStarted = false
    private val mainContainerView by lazy {
        findViewById<View>(R.id.mainContainerLayout)
    }
    private val hitRect = Rect()

    // 트랜지션 이벤트 리스너 -> 완료되면 모션 터지도 끝나므로 false 값으로 줌
    init{
        setTransitionListener(object : TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                motionTouchStarted = false
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }

        })
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->{
                motionTouchStarted = false
                Log.d("MotionEvent0","영역안 터치, $motionTouchStarted")
                return super.onTouchEvent(event)
            }
        }
        if(!motionTouchStarted){
            // 크기를 구한다.
            mainContainerView.getHitRect(hitRect)
            // 영역안에서 포함되는가
            motionTouchStarted = hitRect.contains(event.x.toInt(), event.y.toInt())
            Log.d("MotionEvent0","영역안 터치, $motionTouchStarted")
        }

        return super.onTouchEvent(event) && motionTouchStarted
    }

    // 동작 이벤트 리스너
    // 안넣어주면 다시 돌아가는거 못함
    private val gestureListener by lazy {
        object: GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
                Log.d("MotionEvent(스크롤)", "$e1.toString()   $e2.toString()")
                mainContainerView.getHitRect(hitRect)
                return hitRect.contains(e1.x.toInt(), e1.y.toInt())
            }

        }
    }

    // 제스처 처리
    private val gestureDetector by lazy {
        GestureDetector(context, gestureListener)
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        Log.d("MotionEvent(제스처)", "$event.toString()")
        return gestureDetector.onTouchEvent(event)
    }


}