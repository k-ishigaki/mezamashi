package com.splascope.android.mezamashi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.splascope.phone.mezamashi.createApplicationScreenMessage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<TextView>(R.id.main_text).text = createApplicationScreenMessage()
    }
}