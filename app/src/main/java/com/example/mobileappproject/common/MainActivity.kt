package com.example.mobileappproject.common

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mobileappproject.DBManager.Test
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var fragment1: LmsMainFragment
    lateinit var fragment5: MoreFragment
    lateinit var fragmentManger:FragmentManager

    val initPageIndex:Int=1//처음 프래그먼트
    var backBtnTime = 0L
    var backBtnList =  mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mySQL8이상에서는 DB와 연동하기 전에 안드로이드 정책에 따라 setting할 필요가 있음.
        //만일 아래 문구를 넣지 않을 경우 SQLNonTransientConnectionException이 발생함
        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }


        val bottomNavigationView = binding.navView
        fragmentManger=  supportFragmentManager

        changeFragment(initPageIndex,true)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_menu1 -> {
                    changeFragment(1,true)
                    Log.d("test", "1번 메뉴선택됨")
                    true
                }
                R.id.navigation_menu2 -> {
                    changeFragment(2,true)
                    Log.d("test", "2번 메뉴선택됨")
                    true
                }
                R.id.navigation_menu3 -> {
                    changeFragment(3,true)
                    Log.d("test", "3번 메뉴선택됨")
                    true
                }
                R.id.navigation_menu4 -> {
                    changeFragment(4,true)
                    Log.d("test", "4번 메뉴선택됨")
                    true
                }
                R.id.navigation_menu5 -> {
                    changeFragment(5,true)
                    Log.d("test", "5번 메뉴선택됨")
                    true
                }
            }
            false
        }


        Test.connectiom()
    }
    fun changeFragment(index:Int, change:Boolean){
        if(!backBtnList.contains(index) && change) backBtnList.add(index)

        if(!::fragment1.isInitialized) fragment1 = LmsMainFragment()
        if(!::fragment5.isInitialized) fragment5 = MoreFragment()
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

            }
            5->{
                transaction.replace(R.id.fragment_container,fragment5).commit()
            }
        }
    }


    override fun onBackPressed() {
        if(!backBtnList.isEmpty()){
            Log.d("test",backBtnList.toString())
            val index = backBtnList.removeLast()
            changeFragment(index,false)
            return
        }
        if(System.currentTimeMillis()-backBtnTime > 3000){
            Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
            backBtnTime = System.currentTimeMillis()
            return
        }

        super.onBackPressed()
    }
}