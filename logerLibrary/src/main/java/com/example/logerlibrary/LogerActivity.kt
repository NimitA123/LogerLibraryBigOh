package com.example.logerlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

class LogerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var logDirectoryCreationTimeInMilli:Long = 0
        var logDirectoryCreationTimeInMinute:Long = 0
        Log.d("log", "onCreate: loger")


        //TODO This Current time in the milliSecond
        var currentTime = System.currentTimeMillis()
        //TODO this current time in the Minute.
        var currentMinute = TimeUnit.MILLISECONDS.toMinutes(currentTime)

        if (isExternalStorageWritable) {
          val logDirectory =this.getExternalFilesDir("dir")?.path + File.separator


          val  logFile = File(logDirectory, "logcat_" +  ".txt")


            // create log folder
            if (!logFile.exists()) {
                logFile.mkdir()
                //TODO The file creation time in the milliSecond
                logDirectoryCreationTimeInMilli = System.currentTimeMillis().toLong()
                // TODO Convert milliSecond in minute.
                logDirectoryCreationTimeInMinute = TimeUnit.MILLISECONDS.toMinutes(logDirectoryCreationTimeInMilli)


                Log.d("nimit", "onCreate: ${System.currentTimeMillis()}")
            }
            // TODO file creation time is more than two days than delete file

            if(logFile.exists()){
                if(currentMinute - logDirectoryCreationTimeInMinute >= 3){
                    logFile.delete()
                }
            }

            // clear the previous logcat and then write the new one to the file
            try {
                var process = Runtime.getRuntime().exec("logcat -c")
                process = Runtime.getRuntime().exec("logcat -f $logFile")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else if (isExternalStorageReadable) {
            // only readable
        } else {
            // not accessible
        }

    }


    private val isExternalStorageWritable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state
        }

    /* Checks if external storage is available to at least read */
    private val isExternalStorageReadable: Boolean
        get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
        }


}