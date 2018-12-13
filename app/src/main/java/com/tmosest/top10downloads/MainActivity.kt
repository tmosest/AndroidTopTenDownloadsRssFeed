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
    private var selectedFeedOption = "topfreeapplications"
    private var selectedFeedAmount = "10"

    private fun downLoadAppleInformation() {
        val url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/$selectedFeedOption/limit=$selectedFeedAmount/xml"
        Log.d(tag, "downLoadAppleInformation: called $url")
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
        when (item.itemId) {
            R.id.mnuFree    -> selectedFeedOption = "topfreeapplications"
            R.id.mnuPaid    -> selectedFeedOption = "toppaidapplications"
            R.id.mnuSongs   -> selectedFeedOption = "topsongs"
            R.id.mnuTop10   -> {
                selectedFeedAmount = "10"
                item.isChecked = true
            }
            R.id.mnuTop25   -> {
                selectedFeedAmount = "25"
                item.isChecked = true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        downLoadAppleInformation()
        return true
    }
}
