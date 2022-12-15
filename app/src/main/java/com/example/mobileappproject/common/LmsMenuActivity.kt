package com.example.mobileappproject.common

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileappproject.databinding.ActivityLmsMenuBinding

class LmsMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLmsMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val course_name = intent.getStringExtra("course_name")
        binding.courseName.text = "${course_name}"

        val subjcetCode = intent.getStringExtra("subjcetcode")//todo 가져오기

        binding.menuNotification.setOnClickListener {
            //공지
            val intent = Intent(this, LmsNoticeActivity::class.java)

            intent.putExtra("subjcetCode","CLTR0011-001")
            startActivity(intent)
        }

        binding.menuTextbook.setOnClickListener {
            //강의자료실
            val intent = Intent(this, LmsLectureMaterialActivity::class.java)
            startActivity(intent)
        }

        binding.menuHomework.setOnClickListener {
            //과제
        }

        binding.menuAttendance.setOnClickListener {
            //스마트출결
        }


    }
}