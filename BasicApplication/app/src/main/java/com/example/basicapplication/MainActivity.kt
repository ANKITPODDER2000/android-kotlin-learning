package com.example.basicapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.basicapplication.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private fun isBindingDone(binding: ActivityMainBinding): Boolean =
        (binding.etFirstName.text.toString() != ""
                && binding.etLastName.text.toString() != ""
                && binding.etLocation.text.toString() != ""
                && binding.etPhoneNumber.text.toString() != "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val clickEvt: View.OnClickListener = View.OnClickListener {
            binding.tvUserDetails.text =
                if (isBindingDone(binding)) User(
                    binding.etFirstName.text.toString(),
                    binding.etLastName.text.toString(),
                    binding.etLocation.text.toString(),
                    binding.etPhoneNumber.text.toString()
                ).toString() else "Please Enter all Field"
        }
        binding.btnSubmit.setOnClickListener(clickEvt)
    }


}