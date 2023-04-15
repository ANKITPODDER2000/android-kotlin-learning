package com.example.topapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml"
    private val downloadData by lazy { DownloadData(this, findViewById(R.id.appListView)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        downloadData.execute(url)
    }

    override fun onDestroy() {
        super.onDestroy()
        downloadData.cancel(true)
    }

    companion object {
        private class DownloadData(val context: MainActivity, var listView: ListView) : AsyncTask<String, Void, String>() {
            override fun doInBackground(vararg urlPath: String?): String {
                return URL(urlPath[0]).readText();
            }

            override fun onPostExecute(result: String) {
                val parseData : ParseData = ParseData()
                val apps : ArrayList<App> = parseData.parseData(result)
                listView.adapter = CustomAdapter(context, R.layout.app_list, apps)
            }
        }
    }

}