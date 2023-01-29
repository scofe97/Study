package com.example.ch3_1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService(){

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // 채널 만들기
        createNotificationChannel()

        val type = remoteMessage.data["type"]
            ?.let{NotificationType.valueOf(it)}
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        type ?: return

        // 실제로 알람을 뛰움
        NotificationManagerCompat.from(this)
            .notify(type.id, createNotification(type, title, message))

    }

    private fun createNotificationChannel(){
        // 버전이 O보다 큰경우만 (8.0) 채널을 만들어줌
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 아이디, 이름, 중요도
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)

            // 채널 설명
            channel.description = CHANNEL_DESCRITPION

            // 알림 서비스에 채널 연결
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    private fun createNotification(
        type: NotificationType
        , title : String?
        , message : String?) : Notification{

        val intent = Intent(this, MainActivity::class.java).apply{
            putExtra("notificationType", "${type.title} 타입")
            // 플래그
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(this, type.id,intent,FLAG_UPDATE_CURRENT)

       val notificationBuilder =  NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(title)
            .setContentText(message)
            // 7.1 미만에서는 각 알람마다 중요도 설정해주어야함 (그 이상은 채널에서)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
           .setContentIntent(pendingIntent)
            // 클릭하면 자동으로 지움
           .setAutoCancel(true)

        when(type){
            // Unit -> 함수의 반환구문이 없다 (void 생각)
            NotificationType.NORMAL -> Unit
            NotificationType.EXPANDABLE ->{
                // 확장형 알림 -> 큰 텍스트블록, 혹은 큰 이미지 같은 것
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "😀 😃 😄 😁 😆 😅 😂 🤣 🥲 ☺️ 😊 😇 " +
                                    "🙂 🙃 😉 😌 😍 🥰 😘 😗 😙 😚 😋 😛 " +
                                    "😝 😜 🤪 🤨 🧐 🤓 😎 🥸 🤩 🥳 😏 😒 " +
                                    "😞 😔 😟 😕 🙁 ☹️ 😣 😖 😫 😩 🥺 😢 " +
                                    "😭 😤 😠 😡 🤬 🤯 😳 🥵 🥶 😱 😨 😰 " +
                                    "😥 😓 🤗 🤔 🤭 🤫 🤥 😶 😐 😑 😬 🙄 " +
                                    "😯 😦 😧 😮 😲 🥱 😴 🤤 😪 😵 🤐 🥴 " +
                                    "🤢 🤮 🤧 😷 🤒 🤕"
                        )
                )
            }
            NotificationType.CUSTOM ->{
                //RemoteViews를 사용
                // 커스텀 알림과 위젯에서 자주쓰임 -> 우리앱에서 직접관리하는 앱은 아님
                notificationBuilder.
                        setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(
                        RemoteViews(
                            packageName,
                            R.layout.view_custom_notification
                        ).apply{
                            setTextViewText(R.id.title, title)
                            setTextViewText(R.id.message, message)
                        }
                    )
            }
        }

        return notificationBuilder.build()
    }
    companion object{
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRITPION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel Id"
    }
}