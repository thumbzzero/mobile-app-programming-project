package com.example.mobileappproject


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.example.mobileappproject.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.right_enter,R.anim.none)


        binding.backBtn.setOnClickListener{
            onBackPressed()
        }



    }
}