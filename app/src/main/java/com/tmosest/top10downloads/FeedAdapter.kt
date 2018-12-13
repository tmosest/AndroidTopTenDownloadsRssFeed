package com.tmosest.top10downloads

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/**
 * Example of a view holder pattern
 */
class ViewHolder(v: View) {
    val tvName: TextView = v.findViewById(R.id.tvName)
    val tvArtist: TextView = v.findViewById(R.id.tvArtist)
    val tvSummary: TextView = v.findViewById(R.id.tvSummary)
}

/**
 * Example of a custom adapter
 */
class FeedAdapter(context: Context, val resource: Int, val entries: List<FeedEntry>) : ArrayAdapter<FeedEntry>(context, resource, entries) {
    private val tag = "FeedAdapter"
    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        Log.d(tag, "reused a view ${convertView != null}")
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(resource, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentEntry = entries[position]

        viewHolder.tvName.text = currentEntry.name
        viewHolder.tvArtist.text = currentEntry.artist
        viewHolder.tvSummary.text = currentEntry.summary

        return view
    }
}
