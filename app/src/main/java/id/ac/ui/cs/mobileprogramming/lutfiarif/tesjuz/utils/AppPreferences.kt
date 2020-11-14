package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val NAME = "Tes Juz"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val IS_LOGIN = Pair("is_login", false)
    private val REMINDER_DAYS = "reminderDays"
    private val REMINDER_HOUR = "reminderHour"
    private val REMINDER_MINUTE = "reminderMinute"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var isLogin: Boolean
        get() = preferences.getBoolean(IS_LOGIN.first, IS_LOGIN.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGIN.first, value)
        }

    var reminderDays: String?
        get() = preferences.getString(REMINDER_DAYS, "")
        set(value) = preferences.edit {
            it.putString(REMINDER_DAYS, value)
        }

    var reminderHour: Int
        get() = preferences.getInt(REMINDER_HOUR, -1)
        set(value) = preferences.edit {
            it.putInt(REMINDER_HOUR, value)
        }

    var reminderMinute: Int
        get() = preferences.getInt(REMINDER_MINUTE, -1)
        set(value) = preferences.edit {
            it.putInt(REMINDER_MINUTE, value)
        }
}