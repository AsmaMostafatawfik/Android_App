package com.example.action_buttons

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.action_buttons.ui.theme.Action_buttonsTheme

class MainActivity : ComponentActivity() {
    lateinit var callaction1: Button
    lateinit var sendaction2:Button
    lateinit var browseraction3:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callaction1=findViewById(R.id.button1)
        sendaction2=findViewById(R.id.button2)
        browseraction3=findViewById(R.id.button3)

        callaction1.setOnClickListener {
            val callintint= Intent(Intent.ACTION_DIAL)
            startActivity(callintint)
        }
        sendaction2.setOnClickListener {
            val sentintent=Intent(Intent.ACTION_SENDTO)
            startActivity(sentintent)
        }
        browseraction3.setOnClickListener {
            val mapintent=Intent(Intent.ACTION_VIEW)
            mapintent.data= Uri.parse("https://www.google.com/maps")
            startActivity(mapintent)
        }



    }
}

