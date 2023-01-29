package com.example.ch3_3

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.example.ch3_3.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // step0 뷰를 초기화해주기
        initOnOffButton()
        initChangeAlarmTimeButton()

        // step 1 데이터 가져오기
        val model = fetchDataFromSharedPreferences()

        // step2 뷰에 데이터를 그려주기기
        renderView(model)

    }

    private  fun initOnOffButton(){
        val onOffButton = binding.onOffButton
        onOffButton.setOnClickListener{
            // 데이터를 확인한다
            val model = it.tag as? AlarmDisplayModel ?: return@setOnClickListener
            val newModel = saveAlarmModel(model.hour, model.minute, model.onOff.not())
            renderView(newModel)

            // 온오프 에 따라 작업을 처리
            if(newModel.onOff){
                // 온 -> 알람을 등록
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, newModel.hour)
                    set(Calendar.MINUTE, newModel.minute)

                    // 만약 현재시간보다 이전의 시간값이다
                    if(before(Calendar.getInstance())){
                        add(Calendar.DATE, 1)
                    }
                }

                // 알람 서비스를 가져옴
                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                // 인텐트 설정
                val intent = Intent(this, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT)

                // 알람 반복설정
                // setRepeating() -> 정확 베터리소모 큼
                // setInexactRepeating 부정확 (정각~7분이상 오차) 배터리소모 적음
                alarmManager.setInexactRepeating(
                    // RTC -> 절대시간, ELAPSED -> 부팅이후
                    AlarmManager.RTC_WAKEUP,
                    // 언제 작동하냐
                    calendar.timeInMillis,
                    // 반복하는 기간
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )

            }else{
                // 오프 -> 알람제거
                cancelAlarm()
            }
        }
    }

    private fun initChangeAlarmTimeButton(){
        val changeAlarmButton = binding.changeAlarmTimeButton
        changeAlarmButton.setOnClickListener{
            // 현재시간을 가져옴
            val calendar = Calendar.getInstance()

            // TimePickDialog 사용용 -> 시간설정에 도움을 주는 대화창
            // 지금까지 AlertDialog 만 사용함 ( 타이틀, 메세지, yes 버튼, no 버튼으로 구현 )
            TimePickerDialog(this, { _, hour, minute ->
                // 데이터를 저장
                val model = saveAlarmModel(hour, minute, false)

                // 뷰를 업데이트 한다
                renderView(model)

                // 기존의 알람 삭제
                cancelAlarm()

            },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false )
                .show()
        }
    }

    private fun saveAlarmModel(
        hour : Int,
        minute : Int,
        onOff : Boolean
    ) : AlarmDisplayModel{
        val model = AlarmDisplayModel(
            hour = hour,
            minute = minute,
            onOff = onOff
        )
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()){
            putString(ALARM_KEY, model.makeDataForDB())
            putBoolean(ONOFF_KEY, model.onOff)
            commit()
        }

        return model
    }

    // sharedPreferences 의 정보를 가져옴
    private fun fetchDataFromSharedPreferences() : AlarmDisplayModel {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val timeDBValue = sharedPreferences.getString(ALARM_KEY, "09:30") ?: "09:30"
        val onOffDBValue = sharedPreferences.getBoolean(ONOFF_KEY, false)
        val alarmData = timeDBValue.split(":")

        val alarmModel = AlarmDisplayModel(
            hour = alarmData[0].toInt(),
            minute = alarmData[1].toInt(),
            onOff = onOffDBValue
        )

        // 보정, 보정 예외처리리
        // 알람이 켜져있다고 저장되있는데 실제로 꺼져있거나 반대로인 경우 수정해줘야함
        // NO_CREATE -> 이미 생성된 pendingIntent 가 없다면 null 반환, 아니면 intent 반환 (재사용)
        val pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_NO_CREATE)

        if((pendingIntent == null) and alarmModel.onOff){
            // 알람은 꺼져있는데, 데이터는 켜져있는 경우
            alarmModel.onOff = false
        }else if((pendingIntent != null) and alarmModel.onOff.not()){
            // 알람은 켜져있는데, 데이터는 꺼져있는 경우
            pendingIntent.cancel()
        }

        return alarmModel

    }

    private fun renderView(model : AlarmDisplayModel){
        binding.ampmTextView.apply{
            text = model.ampmText
        }

        binding.timeTextView.apply{
            text = model.timeText
        }

        binding.onOffButton.apply {
            text = model.onOffTExt
            tag = model
        }
    }

    private fun cancelAlarm() {
        val pendingIntent = PendingIntent.getBroadcast(this, ALARM_REQUEST_CODE, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_NO_CREATE)
        pendingIntent?.cancel()
    }


    companion object{
        private const val ALARM_KEY = "alarm"
        private const val ONOFF_KEY = "onOff"
        private const val SHARED_PREFERENCES_NAME = "time"
        private const val ALARM_REQUEST_CODE = 1000
    }
}