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
    }

    fun onClick(v: View) {
        val x = 10
        if (x >= 10)
            Toast.makeText(this, "Je presse mon premier bouton", Toast.LENGTH_LONG).show()
    }
}