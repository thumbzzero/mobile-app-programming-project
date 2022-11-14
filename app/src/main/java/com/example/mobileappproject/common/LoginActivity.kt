package com.example.mobileappproject.common

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    var backBtnTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.findIdTxt.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://appfn.knu.ac.kr/login.knu?agentId=5#link-find-loginid"))
            startActivity(intent)
        }

        binding.loginGuestTxt.setOnClickListener {
            //todo id=guest

            finish()
            overridePendingTransition(R.anim.none,R.anim.none)
        }

    }
    override fun onBackPressed() {
        if(System.currentTimeMillis()-backBtnTime > 3000){
            Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
            backBtnTime = System.currentTimeMillis()
            return
        }

        super.onBackPressed()
    }
}