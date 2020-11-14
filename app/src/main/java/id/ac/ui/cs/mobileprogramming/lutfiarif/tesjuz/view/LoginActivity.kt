package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userViewModel = ViewModelProviders.of(this, UserViewModel.Factory(applicationContext)).get(UserViewModel::class.java)

        buttonLogin.setOnClickListener {
            val isValid = userViewModel.isValidCredential(editTextUserName.toString(), editTextPassword.toString())
            if (isValid) {
                getSharedPreferences("Tes Juz", Context.MODE_PRIVATE).edit().putBoolean("isLogin", true).apply()
                Toast.makeText(baseContext, "Successfully Logged In!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(baseContext, "Invalid login! Please try again.", Toast.LENGTH_SHORT).show()
            }
        }


    }
}