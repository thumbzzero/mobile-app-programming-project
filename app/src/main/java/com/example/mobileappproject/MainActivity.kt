package com.example.mobileappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mobileappproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var fragment1:LmsMainFragment
    lateinit var fragment4:MoreFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bottomNavgationView = binding.navView
        val fragmentManger: FragmentManager = supportFragmentManager

        changeFragment(1,fragmentManger)
        bottomNavgationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_menu1 -> {
                    changeFragment(1, fragmentManger)
                    Log.d("test", "1번 메뉴선택됨")
                    true
                }
                R.id.navigation_menu2 -> {
                    //changeFragment(2, fragmentManger)
                    Log.d("test", "2번 메뉴선택됨")
                    true
                }
                R.id.navigation_menu3 -> {
                    //changeFragment(3, fragmentManger)
                    Log.d("test", "3번 메뉴선택됨")
                    true
                }
                R.id.navigation_menu4 -> {
                    changeFragment(4, fragmentManger)
                    Log.d("test", "4번 메뉴선택됨")
                    true
                }
            }
            false
        }
    }
    fun changeFragment(index:Int,fragmentManger:FragmentManager){
        if(!::fragment1.isInitialized) fragment1 = LmsMainFragment()
        if(!::fragment4.isInitialized) fragment4 = MoreFragment()
        val transaction: FragmentTransaction = fragmentManger.beginTransaction()
        when(index){
            1->{
                transaction.replace(R.id.fragment_container,fragment1).commit()
            }
            2->{

            }
            3->{

            }
            4->{
                transaction.replace(R.id.fragment_container,fragment4).commit()
            }
        }
    }
}