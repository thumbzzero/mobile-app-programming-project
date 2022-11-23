package com.example.mobileappproject.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobileappproject.databinding.FragmentCalenderBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CalenderFragment : Fragment() {
    lateinit var binding: FragmentCalenderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentCalenderBinding.inflate(inflater,container,false)
        val textCalender = binding.tvDate

        //val today= LocalDateTime.now()
        //val current = LocalDateTime.now()
        //val formatter = DateTimeFormatter.ofPattern("yyyy년 / MM월")
        //val formatted = current.format(formatter)
        //textCalender.setText("$formatted")
        super.onCreate(savedInstanceState)


        //val dayList = ArrayList<String>()
        val datas = mutableListOf<String>()


        binding.calenderView.layoutManager = GridLayoutManager(activity,7)
        binding.calenderView.adapter = CalenderAdapter(datas)
        datas.add("일")
        datas.add("월")
        datas.add("화")
        datas.add("수")
        datas.add("목")
        datas.add("금")
        datas.add("토")

        val cal: Calendar = Calendar.getInstance()

        //cal.set(Calendar.MONTH,5)
        cal.set(Calendar.DATE,1)

        val month =cal.get(Calendar.MONTH)
        val year = cal.get(Calendar.YEAR)
        textCalender.setText("${year}년 / ${month+1}월")
        val nWeek: Int = cal.get(Calendar.DAY_OF_WEEK)
        val lastday= cal.getActualMaximum(Calendar.DAY_OF_MONTH)

        //val dayNum: Int = mCal.get(Calendar.DAY_OF_WEEK)
        //1일 - 요일 매칭 시키기 위해 공백 add

        for (i in 1 until nWeek) {
            datas.add("")
        }
        for (i in 1 until lastday+1) {
            datas.add("$i")
        }
        //binding2.calenderView.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))


        return binding.root
    }




}