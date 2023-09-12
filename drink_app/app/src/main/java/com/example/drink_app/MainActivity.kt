package com.example.drink_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.drink_app.ui.theme.Drink_appTheme

class MainActivity : ComponentActivity() {
    lateinit var drinkEt: AutoCompleteTextView
    lateinit var priceEt: TextView
    lateinit var submitBt: Button

    val drinks= mapOf(
        "Water" to 5,
        "Orange juice" to 8
        ,"apple juice" to 10
        ,"tea" to 3
        ,"coffee" to 15
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drinkEt=findViewById(R.id.input_filed)
        priceEt=findViewById(R.id.drink_price)
        submitBt=findViewById(R.id.submit_order)

        val drinkList= listOf("Orange juice","apple juice","tea","coffee","Water")
        val adapter= ArrayAdapter(this,R.layout.list_of_drinks,drinkList)
        drinkEt.setAdapter(adapter)

       drinkEt.setOnItemClickListener { parent, view, position, id ->
           val drink_taken=drinks[drinkEt.text.toString()]
           priceEt.setText(drink_taken.toString())
       }

        submitBt.setOnClickListener {
            val myIntent= Intent(Intent.ACTION_SEND)
            myIntent.type="text/plain"
            val message="your drink ${drinkEt.text.toString()} cost ${priceEt.text.toString()} pounds"
            myIntent.putExtra(Intent.EXTRA_TEXT,message)
            if(myIntent.resolveActivity(packageManager)!=null){
            startActivity(myIntent)
            }
            else{
                Toast.makeText(this,"No activity found",Toast.LENGTH_SHORT).show()
            }

        }


    }
}

