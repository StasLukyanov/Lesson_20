package com.example.lesson_20

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        myViewModel.liveData.observe(this) { binding.edTimer.setText(myViewModel.liveData.value.toString()) }



        binding.apply {
            btnStart.setOnClickListener {
                myViewModel.startCountDownTimer(edTimer.text.toString().toLong())
                binding.tvTimer.text = ""
                binding.checkBox.isChecked = false
                binding.checkBox.visibility = View.GONE
                binding.btnReset.text = "Reset"
            }
        }

        binding.btnPause.setOnClickListener {
            myViewModel.pause()

        }

        binding.btnReset.setOnClickListener {
            binding.checkBox.visibility = View.VISIBLE
            binding.tvTimer.text = "Вы уверены, что хотите сбросить таймер?"
            binding.btnReset.text = "Ok"
            if (binding.checkBox.isChecked) {
                myViewModel.pause()
                binding.edTimer.setText("0")
                binding.tvTimer.text = "Finish"
                binding.btnReset.text = "Reset"
                binding.checkBox.isChecked = false
                binding.checkBox.visibility = View.GONE
                vibro(this)
                Toast.makeText(this,"Таймер аля уля!", Toast.LENGTH_LONG).show()
                Log.d("MyLog","вибрацияяяЯЯЯ!!")
            }
        }
    }



    companion object {
        fun vibro(context: Context) {
            // Kotlin
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val canVibrate: Boolean = vibrator.hasVibrator()
            val milliseconds = 1000L

            if (canVibrate) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // API 26
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            milliseconds,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    // This method was deprecated in API level 26
                    vibrator.vibrate(milliseconds)
                }
            }
        }
    }
}