package com.example.mobileappproject.common

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileappproject.databinding.ActivityNoticeDetailBinding

class NoticeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNoticeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notice_title = intent.getStringExtra("notice_title")
        val notice_writer = intent.getStringExtra("notice_writer")
        val notice_mainTxt = intent.getStringExtra("notice_mainTxt")
        val notice_date = intent.getStringExtra("notice_date")
        val course_code = intent.getStringExtra("course_code")

        Log.d("kkang", "$course_code")
        binding.noticeTitle.text = "${notice_title}"
        binding.noticeWriter.text = "${notice_writer}"
        binding.noticeContent.text = "${notice_mainTxt}"
        binding.noticeDate.text = "${notice_date}"

        binding.backBtn.setOnClickListener {
            
        }

    }
}