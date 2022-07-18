package com.example.ch3_3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 100
        const val NOTIFICATION_CHANNEL_ID = "1000"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // 브로드캐스트 함수에서 주었던 펜딩인텐트가 수신되었을 때 동작

        createNotificationChannel(context)
        notifyNotification(context)

    }

    private fun createNotificationChannel(context : Context){
        // 컨텍스트 -> 실행하고 있는 앱의 상태, 정보를 저장하거나 접근할때 사용
        // 액티비티 자체는 context 를 상속하나, 지금은 브로드캐스트 이므로 context를 따로 받아야함

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "기상 알림",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.description = "알람앱"

            // 채널 생성성
           NotificationManagerCompat.from(context).createNotificationChannel(notificationChannel)
        }
    }

    private fun notifyNotification(context : Context){
        with(NotificationManagerCompat.from(context)) {
            val build = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("알림")
                .setContentTitle("일어날 시간입니다")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            notify(NOTIFICATION_ID, build.build())
        }
    }

}