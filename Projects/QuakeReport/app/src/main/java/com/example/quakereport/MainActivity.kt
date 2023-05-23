package com.example.quakereport

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quakereport.adapter.QuakeReportAdapter
import com.example.quakereport.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var mData: String
    private val mQuakeReportList: MutableList<QuakeReport> = mutableListOf()
    private lateinit var binding: ActivityMainBinding


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = QuakeReportAdapter(mQuakeReportList)
        binding.rvQuakeReport.adapter = adapter
        binding.rvQuakeReport.layoutManager = LinearLayoutManager(this)


        runBlocking {
            launch(Dispatchers.IO) {
                mData = applicationContext.resources.openRawResource(com.example.quakereport.R.raw.quake_data).bufferedReader().use { it.readText() }
                val jsonData = JSONObject(mData)
                val featuresArray = jsonData.getJSONArray("features")
                for(i in 0 until  featuresArray.length()) {
                    featuresArray.getJSONObject(i).getJSONObject("properties").apply {
                        val mag = getDouble("mag")
                        val time = getLong("time")
                        val title = getString("title")
                        val place = getString("place")
                        mQuakeReportList.add(QuakeReport(mag, time, title, place))
                    }
                }
                Log.d("DEBUG_ANKIT", "Dataset is ready")

                adapter.notifyDataSetChanged()
            }
        }
    }


}