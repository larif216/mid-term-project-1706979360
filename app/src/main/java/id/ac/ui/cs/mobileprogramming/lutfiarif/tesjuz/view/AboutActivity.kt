package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.R
import id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.opengl.OpenGLView

class AboutActivity: AppCompatActivity() {

    private lateinit var openGLView: OpenGLView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        openGLView = findViewById(R.id.openGLView)
    }

    override fun onResume() {
        super.onResume()
        openGLView.onResume()
    }

    override fun onPause() {
        super.onPause()
        openGLView.onPause()
    }
}