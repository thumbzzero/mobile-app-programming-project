package com.example.mobileappproject.common


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.right_enter, R.anim.none)


        binding.backBtn.setOnClickListener{
            onBackPressed()
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(isFinishing){
            overridePendingTransition(R.anim.none,R.anim.right_exit)
        }
    }
}