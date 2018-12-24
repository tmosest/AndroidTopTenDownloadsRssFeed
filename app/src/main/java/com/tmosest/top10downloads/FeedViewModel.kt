package com.tmosest.top10downloads

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import java.util.*

private const val TAG = "KotlinViewModel"
val EMPTY_FEED_LIST : List<FeedEntry> = Collections.emptyList()

class FeedViewModel : ViewModel(), DownloadData.DownloaderCallback {

    private var downloadData: DownloadData? = null
    var selectedFeedOption = "topfreeapplications"
    var selectedFeedAmount = "10"
    private var previousSelectedFeedOption = ""
    private var previousSelectedFeedAmount = ""

    private val feed = MutableLiveData<List<FeedEntry>>()
    val feedEntries : LiveData<List<FeedEntry>>
        get() = feed

    init {
        feed.postValue(EMPTY_FEED_LIST)
    }

    fun downLoadAppleInformation() {
        if (previousSelectedFeedOption == selectedFeedOption && previousSelectedFeedAmount == selectedFeedAmount) {
            return
        }
        val url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/$selectedFeedOption/limit=$selectedFeedAmount/xml"
        Log.d(TAG, "downLoadAppleInformation: called $url")
        downloadData = DownloadData(this)
        downloadData?.execute(url)
        previousSelectedFeedAmount = selectedFeedAmount
        previousSelectedFeedOption = selectedFeedOption
    }

    override fun onDataAvailable(data: List<FeedEntry>) {
        feed.value = data
    }
}