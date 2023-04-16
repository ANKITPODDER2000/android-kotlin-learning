package com.example.topapp

import android.view.View
import android.widget.TextView

class ViewHolder (v : View) {
    var appName = v.findViewById<TextView>(R.id.appName)
    var appTitle = v.findViewById<TextView>(R.id.appTitle)
    var appSummary = v.findViewById<TextView>(R.id.appSummary)
}