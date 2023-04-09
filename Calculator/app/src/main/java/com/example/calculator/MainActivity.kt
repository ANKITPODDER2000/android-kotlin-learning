package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    private lateinit var outputTextView: TextView
    private lateinit var inputTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        outputTextView = findViewById(R.id.outputText)
        inputTextView = findViewById(R.id.inputText)

        inputTextView.movementMethod = ScrollingMovementMethod()

        findViewById<ImageView>(R.id.btn_ac).setOnClickListener {
            outputTextView.text = ""
            inputTextView.text = ""
        }

        findViewById<ImageView>(R.id.btn_0).setOnClickListener{inputTextView.append("0")}
        findViewById<ImageView>(R.id.btn_1).setOnClickListener{inputTextView.append("1")}
        findViewById<ImageView>(R.id.btn_2).setOnClickListener{inputTextView.append("2")}
        findViewById<ImageView>(R.id.btn_3).setOnClickListener{inputTextView.append("3")}
        findViewById<ImageView>(R.id.btn_4).setOnClickListener{inputTextView.append("4")}
        findViewById<ImageView>(R.id.btn_5).setOnClickListener{inputTextView.append("5")}
        findViewById<ImageView>(R.id.btn_6).setOnClickListener{inputTextView.append("6")}
        findViewById<ImageView>(R.id.btn_7).setOnClickListener{inputTextView.append("7")}
        findViewById<ImageView>(R.id.btn_8).setOnClickListener{inputTextView.append("8")}
        findViewById<ImageView>(R.id.btn_9).setOnClickListener{inputTextView.append("9")}
        findViewById<ImageView>(R.id.btn_dot).setOnClickListener{inputTextView.append(".")}
        findViewById<ImageView>(R.id.btn_open_bracket).setOnClickListener{inputTextView.append("(")}
        findViewById<ImageView>(R.id.btn_close_bracket).setOnClickListener{inputTextView.append(")")}
        findViewById<ImageView>(R.id.btn_div).setOnClickListener{inputTextView.append("/")}
        findViewById<ImageView>(R.id.btn_mul).setOnClickListener{inputTextView.append("*")}
        findViewById<ImageView>(R.id.btn_minus).setOnClickListener{inputTextView.append("-")}
        findViewById<ImageView>(R.id.btn_plus).setOnClickListener{inputTextView.append("+")}

        findViewById<ImageView>(R.id.btn_equal).setOnClickListener{
            try {
                val ans = ExpressionBuilder(inputTextView.text.toString()).build().evaluate()
                outputTextView.text = (round(ans * 100) / 100).toString()
            }
            catch (e : Exception) {
                outputTextView.text = "Wrong Input!!"
            }
        }
    }
}