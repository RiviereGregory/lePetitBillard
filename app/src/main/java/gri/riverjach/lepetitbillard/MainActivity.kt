package gri.riverjach.lepetitbillard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View) {
        val x = 10
        if (x >= 10)
            Toast.makeText(this, "Je presse mon premier bouton", Toast.LENGTH_LONG).show()
    }
}