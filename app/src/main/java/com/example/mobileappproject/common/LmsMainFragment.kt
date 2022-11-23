package com.example.mobileappproject.common

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileappproject.databinding.FragmentLmsMainBinding
import com.example.mobileappproject.DBManager.DBConnectManager
import com.example.mobileappproject.DBManager.DBInfoManager
import com.example.mobileappproject.DBManager.DBManager
import com.example.mobileappproject.DBManager.DB_dc_std_course_info
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityMainBinding


class LmsMainFragment : Fragment() {
    lateinit var studentId: String
    lateinit var stuCourses: ArrayList<DB_dc_std_course_info>
    lateinit var course: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        studentId = "2016168188"  // 학번 동적으로 수정
        stuCourses = DBManager.getSTDCOS(studentId)

        course = ArrayList<String>()
        for (it in DBInfoManager.dbDcStdCourseInfoMap) {
            if (it.key.second.length > 1) {
                course.add(it.key.second)
            }
        }
         // Log.d("course", "${course}")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLmsMainBinding.inflate(inflater,container,false)

        //Log.d("course", "course.size: ${course.size}")

        for (i in 1 .. course.size) {

            when (i) {
                1 -> {
                    binding.courseLayout1.visibility = View.VISIBLE
                    binding.courseName1.text = course.get(0)
                }
                2 -> {
                    binding.courseLayout2.visibility = View.VISIBLE
                    binding.courseName2.text = course.get(1)
                }
                3 -> {
                    binding.courseLayout3.visibility = View.VISIBLE
                    binding.courseName3.text = course.get(2)
                }
                4 -> {
                    binding.courseLayout4.visibility = View.VISIBLE
                    binding.courseName4.text = course.get(3)
                }
                5 -> {
                    binding.courseLayout5.visibility = View.VISIBLE
                    binding.courseName5.text = course.get(4)
                }
                6 -> {
                    binding.courseLayout6.visibility = View.VISIBLE
                    binding.courseName6.text = course.get(5)
                }
                7 -> {
                    binding.courseLayout7.visibility = View.VISIBLE
                    binding.courseName7.text = course.get(6)
                }
                8 -> {
                    binding.courseLayout8.visibility = View.VISIBLE
                    binding.courseName8.text = course.get(7)
                }

                9 -> {
                    binding.courseLayout9.visibility = View.VISIBLE
                    binding.courseName9.text = course.get(8)
                }

            }

        }

        return binding.root
    }

}