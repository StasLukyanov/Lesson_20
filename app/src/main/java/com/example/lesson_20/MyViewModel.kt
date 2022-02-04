package com.example.lesson_20

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    lateinit var timer: CountDownTimer
    val liveData = MutableLiveData<Long>()


    fun startCountDownTimer(timeSecond: Long) {

        timer = object : CountDownTimer(timeSecond * 1000 + 1000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                liveData.value = millisUntilFinished / 1000

            }

            override fun onFinish() {

            }

        }.start()
    }
    fun pause(){
        timer.cancel()
    }
}