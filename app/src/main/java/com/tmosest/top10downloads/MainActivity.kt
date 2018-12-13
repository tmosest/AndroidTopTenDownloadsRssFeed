package com.tmosest.top10downloads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private var downloadData: DownloadData? = null

    private fun downLoadAppleInformation(feedOption: String = "topfreeapplications") {
        val url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/$feedOption/limit=25/xml"
        downloadData = DownloadData(this, lvTopDownloads)
        downloadData?.execute(url)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag, "onCreate: called")
        downLoadAppleInformation()
        Log.d(tag, "onCreate: done")
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadData?.cancel(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feeds_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val feedOption = when (item.itemId) {
            R.id.mnuFree    -> "topfreeapplications"
            R.id.mnuPaid    -> "toppaidapplications"
            R.id.mnuSongs   -> "topsongs"
            else -> return super.onOptionsItemSelected(item)
        }
        downLoadAppleInformation(feedOption)
        return true
    }
}
