package com.example.flagquizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquizapp.databinding.ActivityMainBinding
import com.example.flagquizapp.lifecycle.MainLifeCycleObserver
import com.example.flagquizapp.util.Security

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(
            "DEBUG_ANKIT",
            "In OnCreate Method.  value of isUnlock is : ${Security.isUnlock}  : ${this.localClassName}"
        )
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MainLifeCycleObserver())
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            Log.d("DEBUG_ANKIT", "Btn click to start play .... : ${this.localClassName}")
            if (binding.etUserName.text.toString().isEmpty()) {
                Toast.makeText(this, "Please give user name..", Toast.LENGTH_SHORT).show()
            } else {
                Intent(this, QuizQuestionActivity::class.java).also {
                    it.putExtra("EXTRA_USER_NAME", binding.etUserName.text.toString())
                    startActivity(it)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!Security.isUnlock)
            Intent(this, SetPinActivity::class.java).also {
                startActivityForResult(it, 2)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_CANCELED && requestCode == 2) finish()
    }
}
