package com.example.mobileappproject.common


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityMypageBinding
import java.io.File

class MypageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.right_enter, R.anim.none)


        binding.backBtn.setOnClickListener{
            onBackPressed()
        }
        binding.logoutTxt.setOnClickListener {
            val filepath = filesDir.path + "/loginInfo.txt"
            val file = File(filepath)
            if(file.exists()) {
                file.delete()
                Log.d("test","로그인정보 삭제")
                onBackPressed()
            }
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(isFinishing){
            overridePendingTransition(R.anim.none,R.anim.right_exit)
        }
    }
}