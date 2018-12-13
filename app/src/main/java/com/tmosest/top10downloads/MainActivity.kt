package com.tmosest.top10downloads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val appleTop10Downloads = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=25/xml"
    private val downloadData: DownloadData by lazy { DownloadData(this, lvTopDownloads) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag, "onCreate: called")
        downloadData.execute(appleTop10Downloads)
        Log.d(tag, "onCreate: done")
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadData.cancel(true)
    }
}
