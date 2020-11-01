package gri.riverjach.lepetitbillard

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : Activity() {

    lateinit var drawingView: DrawingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.vMain)
        drawingView.setWillNotDraw(false)
        drawingView.invalidate()
    }

    override fun onResume() {
        super.onResume()
        drawingView.resume()
    }

    override fun onPause() {
        super.onPause()
        drawingView.pause()
    }

    fun onClick(v: View) {
        drawingView.changeCouleur()
        drawingView.invalidate()
        Toast.makeText(this, "Couleur changé", Toast.LENGTH_SHORT).show()
    }
}