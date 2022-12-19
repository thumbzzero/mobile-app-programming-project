package com.example.mobileappproject.common

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.databinding.ItemNoticeBinding



class NoticeAdapter(val datas: MutableList<String>,val datas2: MutableList<String>,val datas3:MutableList<String>,val datas4:MutableList<Int>,val datas5:MutableList<String> ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class NoticeViewHolder (val binding: ItemNoticeBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder=
        NoticeViewHolder(ItemNoticeBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //Log.d("test2","onBindViewHolder:${position}와${datas4[position]}+${datas[datas4[position]]}/${datas3[datas4[position]]}")
        val binding = (holder as NoticeViewHolder).binding
        binding.title.text = datas[datas4[position]]
        binding.mainTxt.text=datas2[datas4[position]]
        binding.date.text=datas3[datas4[position]]
        binding.title2.text=datas5[datas4[position]]
        binding.itemNotice.setOnClickListener{
            //Log.d("test","onBindViewHolder:$position")
        }
    }
    override fun getItemCount(): Int =datas.size
}