package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ca.antonious.materialdaypicker.MaterialDayPicker
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import kotlinx.android.synthetic.main.activity_reminder.*
import java.util.*

class ReminderActivity: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        initTime()

        btn_set_reminder.setOnClickListener {
            if (dayPicker.selectedDays.isNotEmpty()) {
                val sharedPref = getSharedPreferences("Tes Juz", Context.MODE_PRIVATE)
                val days = sharedPref.edit().putString("reminderDays", getDays(dayPicker.selectedDays)).apply()
                val hour = sharedPref.edit().putInt("reminderHour", timePicker.hour.toInt()).apply()
                val minute = sharedPref.edit().putInt("reminderMinute", timePicker.minute.toInt()).apply()
                Toast.makeText(this, "Reminder set successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please choose minimum 1 day schedule!", Toast.LENGTH_SHORT).show()
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initTime() {
        val sharedPref = getSharedPreferences("Tes Juz", Context.MODE_PRIVATE)
        val savedDays = getDaysInDaysPickerFormat(sharedPref.getString("reminderDays", ""))
        val savedHour = sharedPref.getInt("reminderHour", -1)
        val savedMinute = sharedPref.getInt("reminderMinute", -1)
        dayPicker.setSelectedDays(savedDays)
        timePicker.hour = if (savedHour < 0) Calendar.getInstance().get(Calendar.HOUR_OF_DAY) else savedHour
        timePicker.minute = if (savedMinute < 0) Calendar.getInstance().get(Calendar.MINUTE) else savedMinute
    }

    private fun getDays(selectedDays: List<MaterialDayPicker.Weekday>): String{
        var days = ""
        for (value in selectedDays) {
            days+= "$value,"
        }
        return days.substring(0, days.length - 1)
    }

    private fun getDaysInDaysPickerFormat(selectedDays: String?): List<MaterialDayPicker.Weekday> {
        val result  = mutableListOf<MaterialDayPicker.Weekday>()
        if (selectedDays != "") {
            for (day in selectedDays!!.split(",")) {
                result.add(when(day) {
                    "SUNDAY" -> MaterialDayPicker.Weekday.SUNDAY
                    "MONDAY" -> MaterialDayPicker.Weekday.MONDAY
                    "TUESDAY" -> MaterialDayPicker.Weekday.TUESDAY
                    "WEDNESDAY" -> MaterialDayPicker.Weekday.WEDNESDAY
                    "THURSDAY" -> MaterialDayPicker.Weekday.THURSDAY
                    "FRIDAY" -> MaterialDayPicker.Weekday.FRIDAY
                    "SATURDAY" -> MaterialDayPicker.Weekday.SATURDAY
                    else -> MaterialDayPicker.Weekday.SUNDAY
                })
            }
        }
        return result
    }
}