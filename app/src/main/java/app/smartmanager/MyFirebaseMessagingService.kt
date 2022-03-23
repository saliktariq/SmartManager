package app.smartmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

//Setting constants to be used by class
const val CHANNELID = "notification_channel"
const val CHANNELNAME = "app.smartmanager"

/**
 * Firebase cloud notification implemented after following tutorial at:
 * https://www.youtube.com/watch?v=2xoJi-ZHmNI
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    /*
    Setting notification title and body
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.notification != null){
            makeNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }
    }

    fun getRemoteView(title: String, messageBody: String): RemoteViews{
        val remoteView = RemoteViews(CHANNELNAME, R.layout.cloud_notification)

        remoteView.setTextViewText(R.id.notification_title, title)
        remoteView.setTextViewText(R.id.notification_message, messageBody)
        remoteView.setImageViewResource(R.id.notification_logo,R.drawable.logo400px)
        return remoteView

    }
    fun makeNotification(title: String, messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext, CHANNELID
        )
            .setSmallIcon(R.drawable.logo400px)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1500, 1000, 1500))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
        builder = builder.setContent(getRemoteView(title,messageBody))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(CHANNELID, CHANNELNAME,
            NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0,builder.build())

    }
}