package com.example.burgerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import com.example.burgerproject.databinding.ActivityMainBinding
import com.example.burgerproject.databinding.ToastScreenBinding
import kotlinx.coroutines.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
        binding.ivChecf.visibility = GONE


        var clickEvent: View.OnClickListener = OnClickListener {
            var ans =
                "Your recepie is : \n" + (view.findViewById<RadioButton>(binding.rgRec.checkedRadioButtonId).text as String) + "\n"
            ans += if (binding.cbCheese.isChecked) "Cheese\n" else ""
            ans += if (binding.cbOnion.isChecked) "Onion\n" else ""
            ans += if (binding.cbSalad.isChecked) "Salad\n" else ""

            GlobalScope.launch(Dispatchers.Main) {

                /*
                val t: Toast = Toast(this@MainActivity).apply {
                    duration = Toast.LENGTH_SHORT
                    view = layoutInflater.inflate(R.layout.toast_screen, findViewById(R.id.clToastContainer))
                }
                t.show()
                Log.d("Toast", t.view.toString())
                 */

                binding.ivChecf.visibility = VISIBLE
                binding.tvBurger.text = ""
                Toast.makeText(view.context, "Cooking your Burger...", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "Current Thread is : ${Thread.currentThread().name}")
                delay(2500L)
                Log.d("MainActivity", "Current Thread is : ${Thread.currentThread().name}")
                binding.tvBurger.text = ans
                binding.ivChecf.visibility = GONE

            }

        }
        binding.btnGiveMyBurger.setOnClickListener(clickEvent)
    }
}