package com.example.mobileappproject.data

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mobileappproject.common.MoreNoticeFragment
import com.example.mobileappproject.common.MoreWeekFragment

class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    val fragments: List<Fragment>
    init {
        fragments= listOf( MoreNoticeFragment(),MoreWeekFragment())
        Log.d("test","Fragments size:${fragments.size}")
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }



}