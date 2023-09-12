package com.example.log_in_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import com.example.log_in_page.R.layout.activity_data

class dataActivity : AppCompatActivity() {
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(activity_data)

         tv=findViewById(R.id.show)

        val useremail=intent.getStringExtra("Email")
        val userpassword=intent.getStringExtra("Password")
        val welcom="your email is \n $useremail \n your password is \n $userpassword \n"
        tv.setText(welcom)
    }
}