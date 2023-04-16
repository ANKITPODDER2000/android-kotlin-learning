package com.example.topapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomAdapter(context: Context, val resource: Int, val data: ArrayList<App>) :
    ArrayAdapter<App>(context, resource) {
    override fun getCount(): Int = data.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentApp = data[position]

        viewHolder.appName.text = currentApp.name
        viewHolder.appTitle.text = currentApp.title
        viewHolder.appSummary.text = "${currentApp.summary.substring(0, 100)}...."

        return view
    }
}