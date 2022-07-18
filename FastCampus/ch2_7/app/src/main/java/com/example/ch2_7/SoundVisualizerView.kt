package com.example.ch2_7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class SoundVisualizerView(context : Context, attrs : AttributeSet? = null) : View(context,attrs) {

    // 타입이 Int를 반환하는 함수라는 의미
    var onRequestCurrentAmplitude : (() -> Int)? = null

    // 계단화 방지를 위한 플래그 (곡선이 부드러워짐)
    private val amplitudePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply{
        color = context.getColor(R.color.purple_500)
        strokeWidth = LINE_WIDTH
        strokeCap = Paint.Cap.ROUND
    }
    private var drawingWidth : Int = 0
    private var drawingHeight : Int = 0
    private var drawingAmplitudes : List<Int> = emptyList()
    private var isReplaying : Boolean = false
    private var replayingPosition : Int = 0

    private val visualizeRepeatAction : Runnable = object : Runnable{
        override fun run(){
            if(isReplaying){
                // invoke -> 코틀린에서 인자를 함수로 받을 때 사용한다
                val currentAmplitude = onRequestCurrentAmplitude?.invoke()  ?: 0
                drawingAmplitudes = listOf(currentAmplitude) + drawingAmplitudes
            }else{
                replayingPosition++
            }
            // ondraw 갱신을 위해 사용
            invalidate()

            handler?.postDelayed(this, ACTION_INTERVAL)
        }
    }

    companion object{
        private const val LINE_WIDTH = 10F
        private const val LINE_SPACE = 15F
        // 정수로 나눠버리면 0이나오기 때문
        private const val MAX_AMPLITUDE = Short.MAX_VALUE.toFloat()
        private const val ACTION_INTERVAL = 20L
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        drawingWidth = w
        drawingHeight = h
    }

    override fun onDraw(canvas : Canvas?){
        canvas ?: return

        val centerY = drawingHeight / 2f
        var offsetX = drawingWidth.toFloat()

        drawingAmplitudes
            .let{ amplitudes ->
                if(!isReplaying){
                    // 마지막부터 가져옴
                    amplitudes.takeLast(replayingPosition)
                }else{
                    amplitudes
                }

            }
            .forEach{ amplitude ->
            val lineLength = amplitude / MAX_AMPLITUDE * drawingHeight * 0.8F

            offsetX -= LINE_SPACE
            if(offsetX < 0) return@forEach

            canvas.drawLine(
                offsetX,
                centerY - lineLength / 2F,
                offsetX,
                centerY + lineLength / 2F,
                amplitudePaint
            )
        }
    }

    fun startVisualizing(isReplaying : Boolean){
        this.isReplaying = isReplaying
        handler?.post(visualizeRepeatAction)
    }

    fun stopVisualiztion(){
        replayingPosition = 0
        handler?.removeCallbacks(visualizeRepeatAction)
    }

    fun clearVisualization(){
        drawingAmplitudes = emptyList()
        invalidate()
    }

}