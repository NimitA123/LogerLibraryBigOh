package com.example.logerbigoh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.logerlibrary.MainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*  var myPersonalApp = MyPersonalApp()
        myPersonalApp.onCreate()*/
        var intent = Intent(this,  MainActivity::class.java)
        startActivity(intent)
    }
}