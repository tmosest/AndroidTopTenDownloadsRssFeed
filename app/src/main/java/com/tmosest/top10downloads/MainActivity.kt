package com.tmosest.top10downloads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val appleTop10Downloads = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(tag, "onCreate: called")
        val downloadData = DownloadData()
        downloadData.execute(appleTop10Downloads)
        Log.d(tag, "onCreate: done")
    }
}
