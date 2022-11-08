package com.example.mobileappproject.common


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.right_enter, R.anim.none)


        binding.backBtn.setOnClickListener{
            onBackPressed()
        }



    }
}