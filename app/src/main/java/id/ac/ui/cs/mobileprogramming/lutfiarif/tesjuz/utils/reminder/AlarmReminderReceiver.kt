package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.utils.reminder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.MainActivity

class AlarmReminderReceiver: BroadcastReceiver() {
    private lateinit var mNotification: Notification
    private lateinit var notificationManager: NotificationManager
    private var notificationId = 0

    override fun onReceive(context: Context, intent: Intent) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationId = intent.getIntExtra("notificationId", 0)

        createChannel(context)

        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")
        val notifyIntent = Intent(context, MainActivity::class.java)
        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, notificationId, notifyIntent, 0)

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val res = context.resources
        val pattern = longArrayOf(500, 500)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotification = Notification.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentTitle(title)
                .setStyle(Notification.BigTextStyle().bigText(message))
                .setContentText(message)
                .build()
        } else {
            mNotification = Notification.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(title)
                .setStyle(Notification.BigTextStyle().bigText(message))
                .setSound(uri)
                .setContentText(message)
                .setVibrate(pattern)
                .build()
        }

        notificationManager.notify(notificationId, mNotification)
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            val pattern = longArrayOf(500, 500)
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build()
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = pattern
            notificationChannel.setShowBadge(true)
            notificationChannel.setSound(uri, audioAttributes)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.description = "Reminder"
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        const val CHANNEL_ID = "reminder_notification"
        const val CHANNEL_NAME = "Reminder Notification"
    }
}