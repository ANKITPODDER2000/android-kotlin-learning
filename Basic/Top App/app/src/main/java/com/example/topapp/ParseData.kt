package com.example.topapp

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

const val TAG = "ParseData"
class ParseData {
    fun parseData(data : String) : ArrayList<App> {
        var apps = ArrayList<App> ()
        var isEntry = false
        var tagContent = ""
        var currentApp = App()

        var factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true

        var xpp = factory.newPullParser()
        xpp.setInput(data.reader())

        var eventType = xpp.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    if(xpp.name.lowercase() == "entry") isEntry = true
                }
                XmlPullParser.TEXT -> tagContent = xpp.text
                XmlPullParser.END_TAG -> {
                    val tagName = xpp.name.lowercase()
                    if(isEntry) {
                        when(tagName) {
                            "entry" -> {
                                apps.add(currentApp)
                                isEntry = false
                                currentApp = App()
                            }
                            "name" -> currentApp.name = tagContent
                            "title" -> currentApp.title = tagContent
                            "summary" -> currentApp.summary = tagContent
                            "releaseDate" -> currentApp.releaseDate = tagContent
                        }
                    }
                }
            }
            eventType = xpp.next()
        }
        // for(app in apps) Log.d(TAG, app.toString())
        return apps
    }
}