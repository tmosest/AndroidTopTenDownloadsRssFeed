package com.tmosest.top10downloads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

private const val STATE_AMOUNT = "selectedAmount"
private const val STATE_OPTION = "selectedOption"

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private var downloadData: DownloadData? = null
    private var selectedFeedOption = "topfreeapplications"
    private var selectedFeedAmount = "10"
    private var previousSelectedFeedOption = ""
    private var previousSelectedFeedAmount = ""

    private fun downLoadAppleInformation() {
        if (previousSelectedFeedOption == selectedFeedOption && previousSelectedFeedAmount == selectedFeedAmount) {
            return
        }
        val url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/$selectedFeedOption/limit=$selectedFeedAmount/xml"
        Log.d(tag, "downLoadAppleInformation: called $url")
        downloadData = DownloadData(this, lvTopDownloads)
        downloadData?.execute(url)
        previousSelectedFeedAmount = selectedFeedAmount
        previousSelectedFeedOption = selectedFeedOption
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag, "onCreate: called")
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadData?.cancel(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_AMOUNT, selectedFeedAmount)
        outState.putString(STATE_OPTION, selectedFeedOption)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        selectedFeedAmount = savedInstanceState.getString(STATE_AMOUNT, "10")
        selectedFeedOption = savedInstanceState.getString(STATE_OPTION, "topfreeapplications")
    }

    override fun onResume() {
        super.onResume()
        downLoadAppleInformation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feeds_menu, menu)
        if (selectedFeedAmount == "25") {
            menu?.findItem(R.id.mnuTop25)?.isChecked = true
        }
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
