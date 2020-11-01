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
    var dx: Int
    var dy: Int

    init {
        if (random.nextDouble() > 0.5) dx = 1 else dx = -1
        if (random.nextDouble() < 0.5) dy = 1 else dy = -1
    }

    fun draw(canvas: Canvas?) {
        paint.color = color
        canvas?.drawOval(r, paint)
        paint.textSize = 30f
    }

    fun changeCouleur() {
        color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }

    fun bouge(lesParois: Array<Parois>, lesBalles: ArrayList<Balle>) {
        r.offset(5.0F * dx, 5.0F * dy)
        for (parois in lesParois) {
            parois.gereBalle(this)
        }

        for (balle in lesBalles) {
            if (this !== balle && RectF.intersects(r, balle.r)) {
                balle.rebondit()
                balle.changeCouleur()
                rebondit()
                changeCouleur()
                break
            }
        }
    }

    private fun rebondit() {
        dx = -dx
        dy = -dy
        r.offset(3.0F * dx, 3.0F * dy)
    }

    fun changeDirection(x: Boolean) {
        if (x) {
            this.dy = -dy
        } else {
            this.dx = -dx
        }
        r.offset(3.0F * dx, 3.0F * dy)
    }

}