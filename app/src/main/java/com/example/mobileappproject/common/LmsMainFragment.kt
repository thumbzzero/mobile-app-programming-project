package com.example.mobileappproject.common

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobileappproject.DBManager.DBInfoManager
import com.example.mobileappproject.DBManager.DBManager
import com.example.mobileappproject.DBManager.DB_dc_std_course_info
import com.example.mobileappproject.databinding.FragmentLmsMainBinding


class LmsMainFragment : Fragment() {
    lateinit var studentId: String
    var stuCourses: ArrayList<DB_dc_std_course_info>? = null
    lateinit var course: ArrayList<String>
    lateinit var course_code: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stuCourses = Student.stdCourseInfo
        // Log.d("kkang", "${stuCourses}")
        course = ArrayList<String>()
        course_code = ArrayList<String>()
        if(stuCourses !=null){
            Log.d("test","stucoure 받아와서 리스트에 추가")
            for(it in stuCourses!!){
                course.add(it.sub)
                course_code.add(it.code)
            }
        }else{
            Log.d("test","stucoure null임")
        }


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

        binding.goBtn1.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName1.text
                Log.d("kkang", "${courseName}")
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[0]}")
                startActivity(intent)
            }
        }

        binding.goBtn2.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName2.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[1]}")
                startActivity(intent)
            }
        }

        binding.goBtn3.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName3.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[2]}")
                startActivity(intent)
            }
        }

        binding.goBtn4.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName4.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[3]}")
                startActivity(intent)
            }
        }

        binding.goBtn5.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName5.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[4]}")
                startActivity(intent)
            }
        }

        binding.goBtn6.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName6.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[5]}")
                startActivity(intent)
            }
        }
        binding.goBtn7.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName7.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[6]}")
                startActivity(intent)
            }
        }

        binding.goBtn8.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName8.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[7]}")
                startActivity(intent)
            }
        }

        binding.goBtn9.setOnClickListener {
            activity?.let {
                val intent = Intent(context, LmsMenuActivity::class.java)
                var courseName = binding.courseName9.text
                intent.putExtra("course_name","${courseName}") //데이터 넣기
                intent.putExtra("course_code", "${course_code[8]}")
                startActivity(intent)
            }
        }

        return binding.root
    }

}