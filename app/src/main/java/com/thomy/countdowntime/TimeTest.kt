package com.thomy.countdowntime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.thomy.library.ui.CountDownTime

class TimeTest : AppCompatActivity() {
    private lateinit var countDownTimerView: CountDownTime


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_test)



        countDownTimerView = this.findViewById(R.id.countDownTimerView)


        countDownTimerView.startTimer(
            timerCount = 30,
            onCountDownTimerStarted = {
                Log.d("Timer: ", "Started")
            },
            onCountDownTimerRunning = {
                Log.d("Timer: ", "Running $it")
            },
            onCountDownTimerStopped = {
                Log.d("Timer: ", "Stopped")
            })
    }

}
