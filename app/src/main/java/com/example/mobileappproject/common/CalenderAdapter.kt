package com.example.mobileappproject.common

import android.graphics.Color
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.databinding.ItemCalendarGridviewBinding
import java.util.ArrayList

class CalenderAdapter(val datas: MutableList<String>,val datas2: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class CalenderViewHolder (val binding: ItemCalendarGridviewBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder=
        CalenderViewHolder(ItemCalendarGridviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val a:Int=1
        val binding = (holder as CalenderViewHolder).binding
        binding.tvItem.text = datas[position]

        if(datas2[position]!=""){

            if(datas2[position]=="요일"){
                binding.tvItem2.setHeight(0)
            }else{
                //Log.d("test2","onBindViewHolder:$position")
                binding.tvItem2.text =datas2[position]
                binding.tvItem2.visibility=View.VISIBLE
            }
        }

        binding.itemRoot.setOnClickListener{
            //Log.d("test","onBindViewHolder:$position")
        }

    }


    override fun getItemCount(): Int =datas.size
}