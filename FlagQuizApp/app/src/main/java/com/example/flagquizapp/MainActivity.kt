package com.example.flagquizapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquizapp.databinding.ActivityMainBinding
import com.example.flagquizapp.util.Security

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(
            "DEBUG_ANKIT",
            "In OnCreate Method.  value of isUnlock is : ${Security.isUnlock}  : ${this.localClassName}"
        )
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Intent(this, SetPinActivity::class.java).also { startActivityForResult(it, 2) }

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
        Log.d(
            "DEBUG_ANKIT",
            "OnResume is Called.  value of isUnlock is : ${Security.isUnlock}  : ${this.localClassName}"
        )
        if (!Security.isUnlock)
            Intent(this, SetPinActivity::class.java).also {
                startActivityForResult(it, 2)
            }
    }

    override fun onStart() {
        super.onStart()
        Log.d("DEBUG_ANKIT", "OnStart method is Called : ${this.localClassName}")

    }

    override fun onPause() {
        super.onPause()
        Log.d("DEBUG_ANKIT", "onPause method is Called : ${this.localClassName}")
    }

    override fun onStop() {
        super.onStop()
        Log.d("DEBUG_ANKIT", "onStop method is Called : ${this.localClassName}")
        Security.setIsUnlock(false)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(
            "DEBUG_ANKIT",
            "onRestart method is Called :  value of isUnlock is : ${Security.isUnlock} : ${this.localClassName}"
        )
        if (!Security.isUnlock)
            Intent(this, SetPinActivity::class.java).also {
                startActivityForResult(it, 2)
            }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(
            "DEBUG_ANKIT",
            "onSaveInstanceState method is Called :  value of isUnlock is : ${Security.isUnlock} : ${this.localClassName}"
        )
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?,
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        Log.d(
            "DEBUG_ANKIT",
            "onRestoreInstanceState method is Called :  value of isUnlock is : ${Security.isUnlock} : ${this.localClassName}"
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(
            "DEBUG_ANKIT",
            "On Activity Result for result code : $requestCode  : ${this.localClassName}"
        )
    }
}