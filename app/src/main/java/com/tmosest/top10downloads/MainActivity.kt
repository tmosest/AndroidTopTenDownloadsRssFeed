package com.tmosest.top10downloads

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    private val viewModel by lazy { ViewModelProviders.of(this).get(FeedViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(tag, "onCreate: called")
        viewModel.feedEntries.observe(this, Observer<List<FeedEntry>>{ feedEntries ->
            val feedAdapter = FeedAdapter(this, R.layout.list_record, feedEntries!!)
            lvTopDownloads.adapter = feedAdapter
        })
        viewModel.downLoadAppleInformation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.feeds_menu, menu)
        if (viewModel.selectedFeedAmount == "25") {
            menu?.findItem(R.id.mnuTop25)?.isChecked = true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuFree    -> viewModel.selectedFeedOption = "topfreeapplications"
            R.id.mnuPaid    -> viewModel.selectedFeedOption = "toppaidapplications"
            R.id.mnuSongs   -> viewModel.selectedFeedOption = "topsongs"
            R.id.mnuTop10   -> {
                viewModel.selectedFeedAmount = "10"
                item.isChecked = true
            }
            R.id.mnuTop25   -> {
                viewModel.selectedFeedAmount = "25"
                item.isChecked = true
            }
            else -> return super.onOptionsItemSelected(item)
        }
        viewModel.downLoadAppleInformation()
        return true
    }
}
