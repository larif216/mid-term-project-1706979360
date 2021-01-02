package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.fragments.JuzListFragment
import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

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
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, JuzListFragment())
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.set_reminder_option) {
            startActivity(Intent(this, ReminderActivity::class.java))
            return true
        } else if (id == R.id.about_option) {
            startActivity(Intent(this, AboutActivity::class.java))
        }else if (id == R.id.take_photo_option) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(CAMERA), 1)
                }
            }

            if (ActivityCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), 2)
            } else {
                takePhotoByCamera()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun takePhotoByCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                200 -> {
                    val capturedBitmap = data?.extras?.get("data") as Bitmap
                    saveImage(capturedBitmap)
                }
            }
        }
    }

    private fun saveImage(capturedBitmap: Bitmap) {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "Progress Report Card-${timeStamp}"
        MediaStore.Images.Media.insertImage(contentResolver, capturedBitmap, fileName, "Image of $fileName")
        Toast.makeText(applicationContext, "Image saved to gallery", Toast.LENGTH_SHORT).show()
    }
}