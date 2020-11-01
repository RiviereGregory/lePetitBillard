package gri.riverjach.lepetitbillard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class DrawingView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attributes, defStyleAttr), SurfaceHolder.Callback, Runnable {

    lateinit var canvas: Canvas
    val backgroundPaint = Paint()
    lateinit var thread: Thread
    var drawing = true
    val lesBalles = ArrayList<Balle>()
    lateinit var lesParois: Array<Parois>

    init {
        backgroundPaint.color = Color.WHITE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val canvasH = (h - 500).toFloat()
        val canvasW = (w - 25).toFloat()
        lesParois = arrayOf(
            Parois(5f, 5f, 25f, canvasH),
            Parois(5f, 5f, canvasW, 25f),
            Parois(5f, canvasH, canvasW, canvasH + 25f),
            Parois(canvasW, 5f, canvasW + 25f, canvasH + 25f)
        )
    }

    override fun run() {
        while (drawing) {
            for (balle in lesBalles) {
                balle.bouge(lesParois, lesBalles)
            }
            draw()
        }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0F, 0F, canvas.width * 1F, canvas.height * 1F, backgroundPaint)
            for (balle in lesBalles) {
                balle.draw(canvas)
            }

            for (parois in lesParois) {
                parois.draw(canvas)
            }
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.rawX.toInt() - 100
                val y = event.rawY.toInt() - 300
                if (lesBalles.size < 15) {
                    lesBalles.add(Balle(x.toFloat(), y.toFloat(), 30f))
                }
            }
        }
        return true
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

    override fun surfaceCreated(p0: SurfaceHolder?) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        // Ne fait rien
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        thread.join()
    }
}
