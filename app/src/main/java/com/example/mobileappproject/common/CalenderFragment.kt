package com.example.mobileappproject.common

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileappproject.DBManager.*
import com.example.mobileappproject.databinding.FragmentCalenderBinding
import com.example.mobileappproject.databinding.ItemCalendarGridview2Binding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*

class CalenderFragment : Fragment() {
    lateinit var binding: FragmentCalenderBinding
    //lateinit var binding2: DBManager
    lateinit var studentId: String
    lateinit var stuCourses: ArrayList<DB_dc_std_course_info>
    lateinit var subHw: ArrayList<DB_dc_sub_hwtest>
    //lateinit var subTest: ArrayList<DB_dc_sub_test>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentCalenderBinding.inflate(inflater,container,false)
        //binding2 = DBManager
        //studentId= "2016168188"



        studentId=Student.stdInfo!!.sid // 학번 동적으로 수정
        //Log.d("test2","ID 정보 출력2"+studentId)

        stuCourses = DBManager.getSTDCOS(studentId)
        var notday:Int=7
        val textCalender = binding.tvDate
        //val today= LocalDateTime.now()
        //val current = LocalDateTime.now()
        //val formatter = DateTimeFormatter.ofPattern("yyyy년 / MM월")
        //val formatted = current.format(formatter)
        //textCalender.setText("$formatted")
        super.onCreate(savedInstanceState)


        //val dayList = ArrayList<String>()
        val datas = mutableListOf<String>()
        var datas2 = mutableListOf<String>()
        val title = mutableListOf<String>()
        val main_txt = mutableListOf<String>()
        val date = mutableListOf<String>()
        val count = mutableListOf<Int>()
        val count2 = mutableListOf<Int>()
        val subname = mutableListOf<String>()


        datas.add("일")
        datas.add("월")
        datas.add("화")
        datas.add("수")
        datas.add("목")
        datas.add("금")
        datas.add("토")
        for (i in 1 until 8) {
            datas2.add("요일")
        }

        val cal: Calendar = Calendar.getInstance()

        //cal.set(Calendar.MONTH,10) //셋팅 값의 +1의 달
        cal.set(Calendar.DATE,1)

        var month =cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        textCalender.setText("${year}년 / ${month+1}월")
        val nWeek: Int = cal.get(Calendar.DAY_OF_WEEK)
        val lastday= cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        //val dayNum: Int = mCal.get(Calendar.DAY_OF_WEEK)
        //1일 - 요일 매칭 시키기 위해 공백 add
        //Log.d("test", "날짜 정보 출력" + cal)
        for (i in 1 until nWeek) {
            datas.add("")
            datas2.add("")
            notday++
        }
        for (i in 1 until lastday+1) {

            datas.add("$i")
            datas2.add("")
        }


        var datenow = LocalDate.parse("2022-12-12", DateTimeFormatter.ISO_DATE)
        var i:Int=0;
        var j:Int=0;
        var num2:Int=0;
        for( i in 0..(stuCourses.size-1)){
            //Log.d("test","정보 출력"+stuCourses.get(i).code)
            subHw = DBManager.getSUBHWTEST(stuCourses.get(i).code)
            for( j in 0..(subHw.size-1)){
                if("${year}-${month+1}-32">subHw.get(j).date&&subHw.get(j).date>"${year}-${month+1}-01") {
                    datenow = LocalDate.parse(subHw.get(j).date, DateTimeFormatter.ISO_DATE)
                    num2=datenow.dayOfMonth+nWeek+5;
                    //datas2.add(datenow.dayOfMonth+5+nWeek,subHw.get(j).cri)
                    if(datas2[num2]==""){

                        datas2[num2]=subHw.get(j).cri
                        //Log.d("test2", ",날짜 정보 출력 " + datenow.dayOfMonth+"/"+num2)
                    }

                    //Log.d("test2", ",과목 정보 출력 ${subHw.get(j)}")
                    title.add(subHw.get(j).cri)
                    main_txt.add(subHw.get(j).cont)
                    date.add(subHw.get(j).date)
                    subname.add(subHw.get(j).sub)
                    count.add(0)
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
        for(i in 0..(count.size-1)){
            //Log.d("test","count=${count[i]}")
        }
        for(i in 0..(count.size-1)){
            for(j in (i)..(count.size-1)){
                if(j>i){
                    if(count[j]==count[i]){
                        count[i]=count[i]+1;
                    }
                }
            }
        }

        for(i in 0..(count.size-1)){
            //Log.d("test","count2=${count[i]}")
        }
        //binding2.getSTDINFO(DBManager)

        for(i in 0..(count.size-1)){
            for(j in 0..(count.size-1)){
                if(count[j]==i){
                    count2.add(j)
                }
            }
        }
        val datas3 = mutableListOf<String>()
        for(i in 0..(datas2.size-1)){
            datas3.add(datas2[i])
        }
        //Log.d("test","3번${DBInfoManager.dbDcStdInfoMap}")

        binding.calenderView.layoutManager = GridLayoutManager(activity,7)
        binding.calenderView.adapter = CalenderAdapter(datas,datas3)

        binding.calenderView2.layoutManager=GridLayoutManager(activity,1)
        binding.calenderView2.adapter = CalenderAdapter2(title,main_txt,date,count2,subname)
        //binding2.calenderView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))


        return binding.root
    }




}