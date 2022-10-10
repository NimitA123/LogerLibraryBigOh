package com.example.logerlibrary

import android.app.Application
import android.os.Environment
import java.io.File
import java.io.IOException


class MyPersonalApp : Application() {
    /**
     * Called when the application is starting, before any activity, service, or receiver objects (excluding content providers) have been created.
     */
    override fun onCreate() {
        super.onCreate()
        if (isExternalStorageWritable) {
            val appDirectory =
                File(Environment.getExternalStorageDirectory().toString() + "/MyPersonalAppFolder")
            val logDirectory = File("$appDirectory/logs")
            val logFile = File(logDirectory, "logcat_" + System.currentTimeMillis() + ".txt")

            // create app folder
            if (!appDirectory.exists()) {
                appDirectory.mkdir()
            }

            // create log folder
            if (!logDirectory.exists()) {
                logDirectory.mkdir()
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

    /* Checks if external storage is available for read and write */
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