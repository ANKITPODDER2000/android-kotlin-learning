package com.example.alertdialog

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.alertdialog.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.btnAddToContact.setOnClickListener {
            Log.d(TAG, "DEBUGGGG")
            AlertDialog.Builder(this)
                .setTitle("Add to Contact List")
                .setMessage("Do you want to add Ankit to Contact?")
                .setIcon(R.drawable.ic_user)
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "You added Ankit to Contact", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Not") { _, _ ->
                    Toast.makeText(this, "You didn't add Ankit to Contact", Toast.LENGTH_SHORT).show()
                }
                .create().show()
        }

        val cities: Array<String> = arrayOf("Kolkata", "Bangalore", "Mumbai")
        binding.btnSingleSelect.setOnClickListener {
            Log.d(TAG, "Select Single City")
            AlertDialog.Builder(this)
                .setTitle("Select City")
                .setSingleChoiceItems(cities, 0){
                    dialog, position ->
                    Log.d(TAG, dialog.toString())
                    Log.d(TAG, position.toString())
                    Toast.makeText(this, "You select city : ${cities[position]}", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "You clicked yes", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Not") { _, _ ->
                    Toast.makeText(this, "You clicked no..", Toast.LENGTH_SHORT).show()
                }
                .create().show()
        }

        binding.btnMultipleSelect.setOnClickListener {
            Log.d(TAG, "Select Single City")
            AlertDialog.Builder(this)
                .setTitle("Select City")
                .setMultiChoiceItems(cities, booleanArrayOf(true, false, false)){
                        dialog, position, isChecked ->
                    Log.d(TAG, dialog.toString())
                    Log.d(TAG, position.toString())
                    Toast.makeText(this, "You ${if(isChecked) "Select" else "unselect"} city : ${cities[position]}", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this, "You clicked yes", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Not") { _, _ ->
                    Toast.makeText(this, "You clicked no..", Toast.LENGTH_SHORT).show()
                }
                .create().show()
        }
    }
}