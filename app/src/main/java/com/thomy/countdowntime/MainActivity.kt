package com.thomy.countdowntime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thomy.library.ui.CountDownTime


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timerView = findViewById<CountDownTime>(R.id.short_timer_view)
        timerView.setTime(50000)
        timerView.setOnTimerListener(object : CountDownTime.TimerListener {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                timerView.text = "Time up!"
            }
        })
    }

}