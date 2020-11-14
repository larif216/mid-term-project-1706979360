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
                Toast.makeText(baseContext, R.string.empty_fields_toast, Toast.LENGTH_SHORT).show()
            } else if (editTextPassword.text.toString() != editTextConfirmPassword.text.toString()) {
                Toast.makeText(baseContext, R.string.password_not_match, Toast.LENGTH_SHORT).show()
            } else if (userViewModel.getUser(editTextUserName.text.toString()) != null) {
                Toast.makeText(baseContext, R.string.username_is_exist, Toast.LENGTH_SHORT).show()
            } else {
                userViewModel.createUser(editTextUserName.text.toString(), editTextPassword.text.toString())
                Toast.makeText(baseContext, R.string.success_register, Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        textViewBackToLogin.setOnClickListener {
            finish()
        }
    }
}