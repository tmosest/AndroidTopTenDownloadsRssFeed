package com.tmosest.top10downloads

import android.os.AsyncTask
import android.util.Log
import java.net.URL

class DownloadData : AsyncTask<String, Void, String>() {
    private val tag = "DownloadData"
    override fun doInBackground(vararg urls: String?): String {
        Log.d(tag, "doInBackground parameter is ${urls[0]}")
        val rssFeed = downloadXML(urls[0])
        if (rssFeed.isEmpty()) {
            Log.e(tag, "doInBackground: Error downloading")
        }
        return rssFeed
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        // Log.d(tag, "onPostExecute parameter is $result")
        val parseApplication = ParseApplication()
        parseApplication.parse(result)
    }

    private fun downloadXML(urlPath: String?): String {
        return URL(urlPath).readText()
    }
}