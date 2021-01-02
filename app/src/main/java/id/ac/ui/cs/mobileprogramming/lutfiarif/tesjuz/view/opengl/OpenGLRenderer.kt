package id.ac.ui.cs.mobileprogramming.lutfiarif.tesjuz.view.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.math.sin

class OpenGLRenderer: GLSurfaceView.Renderer {

    private var redValue = 50f
    private var greenValue = 63f
    private var blueValue = 117f

    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        GLES20.glClearColor(redValue, greenValue, blueValue, 1f)
    }

    override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {

    }

    override fun onDrawFrame(p0: GL10?) {
        GLES20.glClearColor(redValue, greenValue, blueValue, 1f)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        redValue = ((sin(System.currentTimeMillis() * 2 * Math.PI / FLASH_DURATION) * .5) + .5).toFloat()
        greenValue = ((sin(System.currentTimeMillis() * 2 * Math.PI / FLASH_DURATION) * .5) + .5).toFloat()
        blueValue = ((sin(System.currentTimeMillis() * 2 * Math.PI / FLASH_DURATION) * .5) + .5).toFloat()
    }

    companion object {
        private const val FLASH_DURATION = 5000
    }
}