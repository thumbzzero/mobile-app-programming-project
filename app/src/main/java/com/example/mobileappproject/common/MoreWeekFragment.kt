package com.example.mobileappproject.common

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.data.NoticeAdapter
import com.example.mobileappproject.data.NoticeInfo
import com.example.mobileappproject.data.NoticeViewHolder
import com.example.mobileappproject.data.WeekInfo
import com.example.mobileappproject.databinding.FragmentMoreWeekBinding
import com.example.mobileappproject.databinding.NoticeDataBinding
import com.example.mobileappproject.databinding.WeekDataBinding


class MoreWeekFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoreWeekBinding.inflate(inflater,container,false)





        val datas = mutableListOf<WeekInfo>()
        for (i in 1..10){
            datas.add(WeekInfo("제목$i","날짜$i","사이트$i","메인----------텍스트$i"))
        }


        val adapter= WeekAdapter(datas)
        val layoutManager = LinearLayoutManager(activity)

        val recyclerView = binding.recyclerViewWeek
        recyclerView.layoutManager=layoutManager

        adapter.setItemClickListener(object : WeekAdapter.OnItemClickListener {
            override fun OnClick(v: View, position: Int) {
                val data = datas[position]


            }
        })
        recyclerView.adapter=adapter









        return binding.root
    }



}


class WeekViewHolder(val binding: WeekDataBinding) : RecyclerView.ViewHolder(binding.root)

class WeekAdapter(val datas: MutableList<WeekInfo>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = WeekViewHolder(WeekDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as WeekViewHolder).binding
        binding.weekSite.text=datas[position].site
        binding.weekTitle.text=datas[position].title
        binding.weekDate.text=datas[position].date
        binding.mainText.text=datas[position].main_text

        binding.data.setOnClickListener {
            when(binding.mainText.visibility){
                View.VISIBLE->binding.mainText.visibility=View.GONE
                View.GONE->binding.mainText.visibility=View.VISIBLE
            }
            itemClickListener.OnClick(it,position)
        }
    }


    interface OnItemClickListener{
        fun OnClick(v: View, position: Int)
    }
    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener:OnItemClickListener){
        this.itemClickListener=itemClickListener
    }

}