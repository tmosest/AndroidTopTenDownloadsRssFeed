package com.tmosest.top10downloads

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

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

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val tag = "DownloadData"
            override fun doInBackground(vararg urls: String?): String {
                Log.d(tag, "doInBackground parameter is ${urls[0]}")
                val rssFeed = downloadXML(urls[0])
                if (rssFeed.isEmpty()) {
                    Log.e(tag, "doInBackground: Error downloading")
                }
                return rssFeed
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(tag, "onPostExecute parameter is $result")
            }

            private fun downloadXML(urlPath: String?): String {
                val xmlResult = StringBuilder()
                try {
                    val url = URL(urlPath)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    val response = connection.responseCode
                    Log.d(tag, "downloadXML: The response code was $response")
                    connection.inputStream.buffered().bufferedReader().use {
                        xmlResult.append(it.readText())
                    }
                } catch (exception: Exception) {
                    val exceptionMessage: String = when (exception) {
                        is MalformedURLException -> "downloadXML: Invalid XML URL ${exception.message}"
                        is IOException -> "downloadXML: IOException reading data ${exception.message}"
                        is SecurityException -> "downloadXML: Security Exception, needs permission ${exception.message}"
                        else -> "downloadXML: Unknown exception ${exception.message}"
                    }
                    Log.e(tag, exceptionMessage)
                }
                return xmlResult.toString()
            }
        }
    }
}
