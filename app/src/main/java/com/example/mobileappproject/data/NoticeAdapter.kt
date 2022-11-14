package com.example.mobileappproject.data

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.databinding.NoticeDataBinding


class NoticeViewHolder(val binding: NoticeDataBinding) :RecyclerView.ViewHolder(binding.root)

class NoticeAdapter(val datas: MutableList<NoticeInfo>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = NoticeViewHolder(NoticeDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as NoticeViewHolder).binding
        binding.noticeSite.text=datas[position].site
        binding.noticeTitle.text=datas[position].title
        binding.noticeDate.text=datas[position].date
        binding.noticeView.text=datas[position].views

        binding.data.setOnClickListener {
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