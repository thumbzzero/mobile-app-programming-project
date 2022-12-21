package com.example.mobileappproject.common

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobileappproject.DBManager.DBManager
import com.example.mobileappproject.DBManager.DB_dc_std_course_info
import com.example.mobileappproject.DBManager.DB_dc_sub_hwtest
import com.example.mobileappproject.databinding.FragmentNoticeBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class NoticeFragment : Fragment() {
    lateinit var studentId: String
    lateinit var binding: FragmentNoticeBinding
    lateinit var stuCourses: ArrayList<DB_dc_std_course_info>
    lateinit var subHw: ArrayList<DB_dc_sub_hwtest>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(inflater,container,false)

        studentId=Student.stdInfo!!.sid // 학번 동적으로 수정
        //Log.d("test2","ID 정보 출력2"+studentId)

        stuCourses = DBManager.getSTDCOS(studentId)

        val cal: Calendar = Calendar.getInstance()
        cal.set(Calendar.DATE,1)

        var month =cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)




        val title = mutableListOf<String>()
        val main_txt = mutableListOf<String>()
        val date = mutableListOf<String>()
        val count = mutableListOf<Int>()
        val count2 = mutableListOf<Int>()
        val subname = mutableListOf<String>()


        var i:Int=0;
        var j:Int=0;
        var x:Int=0;
        for( i in 0..(stuCourses.size-1)){
            //Log.d("test","정보 출력"+stuCourses.get(i).code)
            subHw = DBManager.getSUBHWTEST(stuCourses.get(i).code)
            for( j in 0..(subHw.size-1)){
                if("${year}-${month+1}-${LocalDate.now().dayOfMonth+1}">=subHw.get(j).date) {

                    //Log.d("test", ",과목 정보 출력" + subHw.get(j).cri+subHw.get(j).cont+subHw.get(j).date+subHw.get(j).sub)
                    title.add(subHw.get(j).cri)
                    main_txt.add(subHw.get(j).cont)
                    date.add(subHw.get(j).date)
                    subname.add(subHw.get(j).sub)
                    count.add(x)
                    x=x+1;

                }
            }

        }

        var num:Int=0;
        for(i in 0..(date.size-1)){
            num=0;
            for(j in 0..(date.size-1)){
                if(date[j]<date[i]){
                    num=num+1;
                }
            }
            count[i]=num;
        }
        //Log.d("test","date 정보 출력  "+date)
        //Log.d("test","count 정보 출력  "+count)
        for(i in 0..(count.size-1)){
            num=0;
            for(j in (i)..(count.size-1)){
                if(j>i){
                    if(count[j]==count[i]){
                        num=num+1;
                    }
                }
            }
            count[i]=count[i]+num;
        }

        for(i in 0..(count.size-1)){
            for(j in 0..(count.size-1)){
                if(count[j]==i){
                    count2.add(j)
                }
            }
        }



        //Log.d("test","count 정보 출력  "+count)
        binding.noticeView.layoutManager = GridLayoutManager(activity,1)
        binding.noticeView.adapter = NoticeAdapter(title,main_txt,date,count2,subname)

        return binding.root
    }

}