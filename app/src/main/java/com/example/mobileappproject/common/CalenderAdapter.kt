package com.example.mobileappproject.common

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.databinding.ItemCalendarGridviewBinding

class CalenderAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class CalenderViewHolder (val binding: ItemCalendarGridviewBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder=
        CalenderViewHolder(ItemCalendarGridviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("test","onBindViewHolder:$position")
        val binding = (holder as CalenderViewHolder).binding
        binding.tvItem.text = datas[position]
        binding.itemRoot.setOnClickListener{
            Log.d("test","onBindViewHolder:$position")
        }
    }

    override fun getItemCount(): Int =datas.size
}