package gri.riverjach.lepetitbillard

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.util.*


class Balle(x1: Float, y1: Float, val diametre: Float) {
    val random = Random()
    val paint = Paint()
    val r = RectF(x1, y1, x1 + diametre, y1 + diametre)
    var color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    var showText = false


    fun draw(canvas: Canvas?) {
        paint.color = color
        canvas?.drawOval(r, paint)
        //canvas?.drawText("x ${this.r.centerX()}  y ${this.r.centerY()}", r.right, r.top, paint)
        if (showText) {
            paint.textSize = 30f
            canvas?.drawText("mon diamètre est $diametre", r.left, r.top, paint)
        }
    }

    fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

}