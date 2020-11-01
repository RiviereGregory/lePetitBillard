package gri.riverjach.lepetitbillard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceView
import java.util.*

class DrawingView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attributes, defStyleAttr), Runnable {

    val backgroundPaint = Paint()
    val random = Random()
    val balle1 =
        Balle(random.nextFloat() * 500, random.nextFloat() * 1000, random.nextFloat() * 500)
    val balle2 =
        Balle(random.nextFloat() * 500, random.nextFloat() * 1000, random.nextFloat() * 500)
    val balle3 =
        Balle(random.nextFloat() * 500, random.nextFloat() * 1000, random.nextFloat() * 500)

    lateinit var thread: Thread
    var drawing = true
    lateinit var canvas: Canvas

    fun changeCouleur() {
        balle1.changeCouleur()
        balle2.changeCouleur()
        balle3.changeCouleur()
    }


    override fun run() {
        while (drawing) {
            draw()
        }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            backgroundPaint.color = Color.WHITE
            canvas.drawRect(0F, 0F, canvas.width * 1F, canvas.height * 1F, backgroundPaint)
            balle1.bouge(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    fun pause() {
        drawing = false
        thread.join()
    }
}
