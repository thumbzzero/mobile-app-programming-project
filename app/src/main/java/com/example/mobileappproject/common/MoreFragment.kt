package com.example.mobileappproject.common

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.mobileappproject.data.MyFragmentPagerAdapter
import com.example.mobileappproject.databinding.FragmentMoreBinding
import com.google.android.material.tabs.TabLayoutMediator


class MoreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoreBinding.inflate(inflater,container,false)


        val myAdapter = MyFragmentPagerAdapter(requireActivity())

        binding.viewpager.adapter = myAdapter
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("test","페이지 변경됨->$position")
            }
        })

        TabLayoutMediator(binding.tabLayout,binding.viewpager){tab,position->
            when(position){
                0-> tab.text="공지사항"
                1-> tab.text="주간 Knu"
            }
        }.attach()

        binding.myPageImg.setOnClickListener {
            Log.d("test","액티비티 호출")
            val intent =Intent(requireContext(), MypageActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }


}