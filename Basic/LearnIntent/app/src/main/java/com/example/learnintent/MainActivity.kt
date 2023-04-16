package com.example.learnintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.learnintent.databinding.ActivityMainBinding
import java.net.URI

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view: View = binding.root
        setContentView(view)

        binding.btnNext.setOnClickListener {
            Intent(this, SecondaryActivity::class.java).also {
                it.putExtra("PASS_DATA", PassData("Home", 1, true))
                startActivity(it)
            }
        }

        binding.btnImageOpener.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, 0)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == 0) {
            val image: Uri? = data?.data
            binding.ivImage.setImageURI(image)
        }
    }
}