package com.example.log_in_page

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
import com.example.log_in_page.ui.theme.Log_in_pageTheme

class MainActivity : ComponentActivity() {
    lateinit var emailet: EditText
    lateinit var passet:EditText
    lateinit var click:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        click =findViewById(R.id.button)
        emailet=findViewById(R.id.editText)
        passet=findViewById(R.id.editText2)



        click.setOnClickListener {
            val email=emailet.getText().toString()
            val password=passet.getText().toString()

            val myintent= Intent(this,dataActivity::class.java)
             myintent.putExtra("Email",email)
            myintent.putExtra("Password",password)

            startActivity(myintent)
        }
    }

}


