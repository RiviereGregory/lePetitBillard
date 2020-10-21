package gri.riverjach.lepetitbillard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import java.util.*

class DrawingView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attributes, defStyleAttr) {

    val backgroundPaint = Paint()
    val random = Random()
    val balle1 =
        Balle(random.nextFloat() * 500, random.nextFloat() * 1000, random.nextFloat() * 500)
    val balle2 =
        Balle(random.nextFloat() * 500, random.nextFloat() * 1000, random.nextFloat() * 500)
    val balle3 =
        Balle(random.nextFloat() * 500, random.nextFloat() * 1000, random.nextFloat() * 500)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        backgroundPaint.color = Color.WHITE
        canvas?.drawRect(0F, 0F, width.toFloat(), height.toFloat(), backgroundPaint)
        balle1.draw(canvas)
        balle2.draw(canvas)
        balle3.draw(canvas)
    }

    fun changeCouleur(){
        balle1.changeCouleur()
        balle2.changeCouleur()
        balle3.changeCouleur()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(event.action == MotionEvent.ACTION_DOWN){
            val x = event.rawX
            val y = event.rawY - 260
            //Toast.makeText(context, "x $x y $y", Toast.LENGTH_SHORT).show()
            if (balle1.r.contains(x,y)) balle1.showText = !balle1.showText
            if (balle2.r.contains(x,y)) balle2.showText = !balle2.showText
            if (balle3.r.contains(x,y)) balle3.showText = !balle3.showText
        }
        invalidate()
        return true
    }
}
