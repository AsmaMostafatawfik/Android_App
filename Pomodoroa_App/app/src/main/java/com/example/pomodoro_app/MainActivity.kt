package com.example.pomodoro_app

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {


    lateinit var wordTV: TextView
    lateinit var timeTV:TextView
    lateinit var startBt:Button
    lateinit var resetTV:TextView
    lateinit var circlePG: ProgressBar
    var timer:CountDownTimer?=null
    val start_time_in_mm:Long=25*60*1000
    var remining_time:Long=start_time_in_mm
    var isRunning=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordTV=findViewById(R.id.take_a_pomodoro)
        timeTV=findViewById(R.id.time)
        startBt=findViewById(R.id.start)
        resetTV=findViewById(R.id.reset)
        circlePG=findViewById(R.id.progressBar)

        startBt.setOnClickListener {
            if(!isRunning){
            startTimer(start_time_in_mm)
            }

        }
        resetTV.setOnClickListener {
            resetTimer()
        }

    }



    private fun startTimer(startTime:Long) {
        timer = object : CountDownTimer(startTime, 1*1000) {

            override fun onTick(timeLeft: Long) {
                remining_time=timeLeft
                updateTimer()
                circlePG.progress=remining_time.toDouble().div(start_time_in_mm.toDouble()).times(100).toInt()

            }

            override fun onFinish() {
             Toast.makeText(this@MainActivity,"end",Toast.LENGTH_SHORT).show()
                isRunning=false
            }

        }.start()
        wordTV.text=resources.getText(R.string.keep_going)
        isRunning=true
    }
    private fun resetTimer() {
        timer?.cancel()
        remining_time=start_time_in_mm
        wordTV.text=resources.getText(R.string.take_a_pomodoro)
        updateTimer()
        circlePG.progress=100
        isRunning=false
    }
    private fun updateTimer(){
        val minute =remining_time.div(1000).div(60)
        val second =remining_time.div(1000) % 60
        val formattedTime =String.format(" %02d:%02d",minute,second)
        timeTV.text=formattedTime
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("Remaining Time",remining_time)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedTime=savedInstanceState.getLong("Remaining Time")
        if(savedTime!=start_time_in_mm)
        startTimer(savedTime)
    }
}

