package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLogin = getSharedPreferences("Tes Juz", Context.MODE_PRIVATE).getBoolean("isLogin", false)

        if (!isLogin) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            setContentView(R.layout.activity_main)
        }

    }
}