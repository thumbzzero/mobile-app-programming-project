package com.example.mobileappproject.common

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
    }
}