package com.example.mobileappproject.common

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.databinding.ItemCalendarGridview2Binding



class CalenderAdapter2 (val datas: MutableList<String>,val datas2: MutableList<String>,val datas3:MutableList<String>,val datas4:MutableList<Int>,val datas5:MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class CalenderViewHolder (val binding: ItemCalendarGridview2Binding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder=
        CalenderViewHolder(ItemCalendarGridview2Binding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("test","onBindViewHolder:$position")
        val binding = (holder as CalenderViewHolder).binding
        binding.title.text = datas[datas4[position]]
        binding.mainTxt.text=datas2[datas4[position]]
        binding.date.text=datas3[datas4[position]]
        binding.title2.text=datas5[datas4[position]]
        binding.itemRoot2.setOnClickListener{
            Log.d("test","onBindViewHolder:$position")
        }
    }


    override fun getItemCount(): Int =datas.size
}