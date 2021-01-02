package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class OpenGLView(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs) {
    init {
        setEGLContextClientVersion(2)
        preserveEGLContextOnPause = true
        setRenderer(OpenGLRenderer())
    }
}