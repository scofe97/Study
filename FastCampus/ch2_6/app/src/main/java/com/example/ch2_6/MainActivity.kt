package com.example.ch2_6

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.ch2_6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val remainMinutesTextView : TextView by lazy{
        binding.remainMinutesTextView
    }

    private val remainSecondsTextView : TextView by lazy{
        binding.remainSecondsTextView
    }

    private val seekBar : SeekBar by lazy{
        binding.seekBar
    }

    private var currentCountDownTimer : CountDownTimer? = null

    private val soundPool = SoundPool.Builder().build()

    private var tickingSoundId : Int? = null
    private var bellSoundId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bindViews()
        initSount()
    }

    override fun onResume() {
        super.onResume()
        soundPool.autoResume()
    }

    override fun onPause() {
        super.onPause()
        soundPool.autoPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

    private fun bindViews(){
        seekBar.setOnSeekBarChangeListener(
            object  : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // 사용자가 건드린 경우만 업데이트함
                    if(fromUser){
                        // 한자리수여도 01처럼 나옴
                        updateRemainTime(progress * 60 * 1000L)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // 현재 카운트다운 멈춤
                    stopCountDown()
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                    // 엘비스 연산자 -> 좌측의 값이 null이면 우측값 리턴
                   seekBar ?: return

                    if(seekBar.progress == 0){
                        stopCountDown()
                    }else{
                        startCoundDown()
                    }

                }
        })
    }

    private fun initSount(){
        tickingSoundId = soundPool.load(this, R.raw.timer_ticking, 1)
        bellSoundId = soundPool.load(this,R.raw.timer_bell,1)
    }

    private fun createCountDownTimer(initialMills : Long) =
        object : CountDownTimer(initialMills, 1000L){
            override fun onTick(millisUntilFinished: Long) {
                updateRemainTime(millisUntilFinished)
                updateSeekBar(millisUntilFinished)
            }

            override fun onFinish() {
                completeCountDown()
            }
        }

    private fun startCoundDown(){
        currentCountDownTimer = createCountDownTimer(seekBar.progress * 60 * 1000L)
        currentCountDownTimer?.start()

        // 음악이름, 좌우소리 (1이최대), ?, -1(무한반복), 재생속도
        // 앱을 종료해도 소리가 그대로 들리는데 디바이스 자체에 요청하기 떄문임
        tickingSoundId?.let{ soundId ->
            soundPool.play(soundId, 1F, 1F, 0, -1, 1F)
        }
    }

    private fun stopCountDown(){
        currentCountDownTimer?.cancel()
        currentCountDownTimer = null
        soundPool.autoPause()
    }

    private fun completeCountDown(){
        updateRemainTime(0)
        updateSeekBar(0)

        soundPool.autoResume()
        bellSoundId?.let{ soundId ->
            soundPool.play(soundId,1F,1F,0,0,1F)
        }
    }

    private fun updateRemainTime(remainMillls : Long){

        val remainSeconds = remainMillls / 1000

        remainMinutesTextView.text = "%02d'".format(remainSeconds / 60)
        remainSecondsTextView.text = "%02d".format(remainSeconds % 60)
    }

    private fun updateSeekBar(remainMills : Long){
        seekBar.progress = (remainMills / 1000 / 60).toInt()
    }

}