package com.example.memeapp

import android.os.AsyncTask
import org.json.JSONObject
import java.net.URL

private const val TAG = "DownloadData"
class DownloadData () : AsyncTask<String, Void, String>() {
    private lateinit var updateMeme : (Meme) -> Unit
    override fun doInBackground(vararg urlPath: String?): String {
        return URL(urlPath[0]).readText()
    }

    fun setMemeInstance(updateMeme : (Meme) -> Unit) {
        this.updateMeme = updateMeme
    }

    override fun onPostExecute(result: String) {
        // Log.d(TAG, result)
        val obj = JSONObject(result)
        val title = obj.getString("title")
        val url = obj.getString("url")
        val meme = Meme(title = title, url = url)
        updateMeme(meme)
    }
}