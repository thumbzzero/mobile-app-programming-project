package com.example.mobileappproject.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileappproject.databinding.FragmentMoreWeekBinding


class MoreWeekFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoreWeekBinding.inflate(inflater,container,false)

        return binding.root
    }

}