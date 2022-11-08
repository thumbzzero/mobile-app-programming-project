package com.example.mobileappproject.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileappproject.data.NoticeAdapter
import com.example.mobileappproject.data.NoticeInfo
import com.example.mobileappproject.databinding.FragmentMoreNoticeBinding


class MoreNoticeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoreNoticeBinding.inflate(inflater,container,false)

        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager=layoutManager
        val datas = mutableListOf<NoticeInfo>()



        for(i in 1..9){
            datas.add(NoticeInfo("제목$i","날짜$i","사이트$i","조회수 $i","test"))
        }



        val adapter=NoticeAdapter(datas)
        binding.recyclerView.adapter=adapter






        return binding.root
    }


}