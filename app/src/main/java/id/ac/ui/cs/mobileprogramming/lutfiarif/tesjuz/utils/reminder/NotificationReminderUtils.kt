package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.utils.reminder

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import java.util.*

class NotificationReminderUtils {
    var context: Context? = null

    fun setNotification (ctx: Context?) {
        context = ctx
        val sharedPref = context!!.getSharedPreferences("Tes Juz", Context.MODE_PRIVATE)
        setUpAlarms(sharedPref)
    }

    private fun setUpAlarms(sharedPref: SharedPreferences) {
        val activeDays = getIntDays(sharedPref.getString("reminderDays", ""))
        val hour = sharedPref.getInt("reminderHour", -1)
        val minute = sharedPref.getInt("reminderMinute", -1)

        cancelAllAlarms(context)

        for (day in activeDays) {
            if (day != "") scheduleAlarm(day.toInt(), hour, minute)
        }
    }

    private fun scheduleAlarm(dayOfWeek: Int, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 7)
        }

        if (calendar.timeInMillis > 0) {
            val alarmManager = context?.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReminderReceiver::class.java)
            val sharedPref = context!!.getSharedPreferences("Tes Juz", Context.MODE_PRIVATE)
            alarmIntent.putExtra("title", R.string.reminder)
            alarmIntent.putExtra("message", R.string.reminder_message)
            alarmIntent.putExtra("notificationId", dayOfWeek)
            val pendingIntent = PendingIntent.getBroadcast(context, dayOfWeek, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY * 7, pendingIntent)
        }
    }

    private fun cancelAllAlarms(context: Context?) {
        for (i in 1..7) {
            val alarmManager = context?.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReminderReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, i, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun getIntDays(activeDays: String?): Array<String> {
        var result = ""
        for (day in activeDays!!.split(",")) {
            result += when(day) {
                "SUNDAY" -> "1,"
                "MONDAY" -> "2,"
                "TUESDAY" -> "3,"
                "WEDNESDAY" -> "4,"
                "THURSDAY" -> "5,"
                "FRIDAY" -> "6,"
                "SATURDAY" -> "7,"
                else -> ""
            }
        }
        result = result.substring(0, result.length - 1)
        return result.split(",").toTypedArray()
    }
}