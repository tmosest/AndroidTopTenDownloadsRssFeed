package com.tmosest.top10downloads

import android.os.AsyncTask
import android.util.Log
import java.net.URL

private val TAG = "DownloadData"
/**
 * Example of a custom asynchronous task.
 */
class DownloadData(private val downloaderCallback: DownloaderCallback) : AsyncTask<String, Void, String>() {

    interface DownloaderCallback {
        fun onDataAvailable(data: List<FeedEntry>)
    }

    override fun doInBackground(vararg urls: String): String {
        Log.d(TAG, "doInBackground parameter is ${urls[0]}")
        val rssFeed = downloadXML(urls[0])
        if (rssFeed.isEmpty()) {
            Log.e(TAG, "doInBackground: Error downloading")
        }
        return rssFeed
    }

    override fun onPostExecute(result: String) {
        val parseApplication = ParseApplication()
        if (result.isNotEmpty()) {
            parseApplication.parse(result)
        }
        downloaderCallback.onDataAvailable(parseApplication.applications)
    }

    private fun downloadXML(urlPath: String?): String {
        try {

        } catch (exception : Exception) {
            Log.d(TAG, exception.message)
        }
        return URL(urlPath).readText()
    }
}
