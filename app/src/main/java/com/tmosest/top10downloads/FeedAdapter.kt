package com.tmosest.top10downloads

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class FeedAdapter(context: Context, val resource: Int, val entries: List<FeedEntry>) : ArrayAdapter<FeedEntry>(context, resource, entries) {
    private val tag = "FeedAdapter"
    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.d(tag, "reused a view ${convertView != null}")
        val view: View = convertView ?: inflater.inflate(resource, parent, false)

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvArtist = view.findViewById<TextView>(R.id.tvArtist)
        val tvSummary = view.findViewById<TextView>(R.id.tvSummary)

        val currentEntry = entries[position]

        tvName.text = currentEntry.name
        tvArtist.text = currentEntry.artist
        tvSummary.text = currentEntry.summary

        return view
    }
}