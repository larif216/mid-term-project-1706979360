package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import android.widget.Toast

class ToastUtils(
    private val context: Context,
    private val text: String,
    private val type: Int
) {
    fun show() {
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)

        val color: Int = if (type == 0) {
            Color.parseColor("#BB3446")
        } else {
            Color.parseColor("#4CCD43")
        }

        val shape = GradientDrawable()
        shape.cornerRadius = 90F
        shape.setColor(color)

        val view = toast.view
        view.background = shape

        val tv = view.findViewById<TextView>(android.R.id.message)
        tv.setTextColor(Color.WHITE)
        toast.show()
    }
}