package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val userViewModel = ViewModelProviders.of(this, UserViewModel.Factory(applicationContext)).get(
            UserViewModel::class.java)

        buttonRegister.setOnClickListener {
            if (editTextUserName.text.toString() == "" || editTextPassword.text.toString() == "" || editTextConfirmPassword.text.toString() == "") {
                Toast.makeText(baseContext, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            } else if (editTextPassword.text.toString() != editTextConfirmPassword.text.toString()) {
                Toast.makeText(baseContext, "Password not match!", Toast.LENGTH_SHORT).show()
            } else if (userViewModel.getUser(editTextUserName.text.toString()) != null) {
                Toast.makeText(baseContext, "Username is exist!", Toast.LENGTH_SHORT).show()
            } else {
                userViewModel.createUser(editTextUserName.text.toString(), editTextPassword.text.toString())
                Toast.makeText(baseContext, "Successfully register!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        textViewBackToLogin.setOnClickListener {
            finish()
        }
    }
}