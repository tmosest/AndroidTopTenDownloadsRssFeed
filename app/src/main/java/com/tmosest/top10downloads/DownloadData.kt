package com.tmosest.top10downloads

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import java.net.URL
import kotlin.properties.Delegates

class DownloadData(context: Context, listView: ListView) : AsyncTask<String, Void, String>() {
    private val tag = "DownloadData"

    private var context: Context by Delegates.notNull()
    private var listView: ListView by Delegates.notNull()

    init {
        this.context = context
        this.listView = listView
    }

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

        val arrayAdapter = ArrayAdapter<FeedEntry>(context, R.layout.list_item, parseApplication.applications)
        listView.adapter = arrayAdapter
    }

    private fun downloadXML(urlPath: String?): String {
        return URL(urlPath).readText()
    }
}