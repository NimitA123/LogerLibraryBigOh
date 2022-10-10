package com.example.logerlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myPersonalApp = MyPersonalApp()
        myPersonalApp.onCreate()

    }
}