package com.example.ch2_7

import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.media.MediaRecorder.AudioEncoder.AMR_NB
import android.media.MediaRecorder.OutputFormat.AMR_NB
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.ch2_7.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private val soundVisualizerView : SoundVisualizerView by lazy{
        binding.soundVisualizerView
    }

    private lateinit var binding: ActivityMainBinding

    private val requiredPermission = arrayOf(android.Manifest.permission.RECORD_AUDIO)

    private var recorder: MediaRecorder? = null

    private val recordButton: RecordButton by lazy {
        binding.recordButton
    }
    private val resetButton : Button by lazy{
        binding.resetButton
    }
    private val recordTimeTextView : CountUpView by lazy{
        binding.recordTimeTextView
    }

    private var state = State.BEFORE_RECORDING
    set(value){
        // get set 내부에서 게터 세터가 호출되지 않도록 해줌
        field = value
        resetButton.isEnabled = (value == State.AFTER_RECORDING) || (value == State.ON_PLAYING)
        recordButton.updateIconWithState(value)
    }

    private val recordingFilePath: String by lazy {
        // 녹음파일을 저장할 경로
        "${externalCacheDir?.absolutePath}/recording.3gp"
    }

    private var player: MediaPlayer? = null

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 201
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        requestAudioPermission()
        initViews()
        bindViews()
        // 세터를 발생시키기 위해 넣어줌 (전역 초기화는 발생안함)
        initVariables()
    }

    private fun initViews() {
        recordButton.updateIconWithState(state)
    }

    private fun bindViews(){
        // 값을 받음, 함수가 호출될때 값을 반환
        soundVisualizerView.onRequestCurrentAmplitude = {
            recorder?.maxAmplitude ?: 0
        }
        recordButton.setOnClickListener{
            when(state){
                State.BEFORE_RECORDING ->{
                    startRecording()
                }
                State.ON_RECORDING ->{
                    stopRecording()
                }
                State.AFTER_RECORDING ->{
                    startPlaying()
                }
                State.ON_PLAYING ->{
                    stopPlaying()
                }
            }
        }
        resetButton.setOnClickListener{
            stopPlaying()
            soundVisualizerView.clearVisualization()
            recordTimeTextView.clearCountTime()
            state = State.BEFORE_RECORDING
        }
    }

    private fun initVariables(){
        state = State.BEFORE_RECORDING
    }


    private fun requestAudioPermission() {
        requestPermissions(requiredPermission, REQUEST_RECORD_AUDIO_PERMISSION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted = requestCode == REQUEST_RECORD_AUDIO_PERMISSION &&
                // 부여결과중에서 배열을 이용하는데 하나만 사용해서 FIRST사용
                grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (!audioRecordPermissionGranted) {
            finish()
        }
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            // 마이크 접근
            setAudioSource(MediaRecorder.AudioSource.MIC)
            // 디코더
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            // 인코더
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            // 저장파일
            // 앱에서 정근할수있는 저장소가 한정됨 내부 저장소에는 용량의 한계가 있음
            // 그러므로 외부파일에 접근해서 저장해야함 (캐쉬 저장파일)
            setOutputFile(recordingFilePath)
            // 녹음 준비완료
            prepare()
        }
        recorder?.start()
        soundVisualizerView.startVisualizing(false)
        recordTimeTextView.statCountUp()
        state = State.ON_RECORDING
    }

    private fun stopRecording() {
        recorder?.run {
            stop()
            release()
        }
        recorder = null
        soundVisualizerView.stopVisualiztion()
        recordTimeTextView.stopCountUp()
        state = State.AFTER_RECORDING
    }

    private fun startPlaying() {
        // MediaPlayer
        player = MediaPlayer().apply {
            setDataSource(recordingFilePath)
            prepare()
        }

        player?.setOnCompletionListener {
            stopPlaying()
            state = State.AFTER_RECORDING
        }

        player?.start()
        soundVisualizerView.startVisualizing(true)
        recordTimeTextView.statCountUp()
        state = State.ON_PLAYING

    }

    private fun stopPlaying() {
        // release를 하면 언제든지 end 상태로 가기때문에 stop을 해줄필요는 없음
        player?.release()
        player = null
        soundVisualizerView.stopVisualiztion()
        recordTimeTextView.stopCountUp()
        state = State.AFTER_RECORDING
    }
}