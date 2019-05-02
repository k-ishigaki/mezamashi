package io.github.k_ishigaki.mezamashi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<TextView>(R.id.main_text).text = createApplicationScreenMessage()

        callDelayed {
            runOnUiThread {
                findViewById<TextView>(R.id.main_text).text = "timer end"
            }
        }
    }
}