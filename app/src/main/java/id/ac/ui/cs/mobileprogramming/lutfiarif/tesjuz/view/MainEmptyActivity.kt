package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class MainEmptyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val isLogin = getSharedPreferences("Tes Juz", Context.MODE_PRIVATE).getBoolean("isLogin", false)

        if (isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        finish()
    }
}