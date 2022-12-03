package com.example.mobileappproject.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.DBManager.DBManager
import com.example.mobileappproject.DataManager.LoginData
import com.example.mobileappproject.databinding.ActivityLmsLectureMaterialBinding
import com.example.mobileappproject.databinding.LmsLecturematerialDataBinding
import com.example.mobileappproject.databinding.LmsNoticeDataBinding

class LmsLectureMaterialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLmsLectureMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("강의자료")

        //recieve data from DB
        val studentSID = DBManager.getSTDINFO(LoginData.id, LoginData.pw).sid
        val lecmat = DBManager.getSUBLECMAT(studentSID)


        val datas = mutableListOf<LmsLectureMaterial>()
        for(i in 1.. 10){
            datas.add(LmsLectureMaterial("$i", "$i"))
        }

        binding.recyclerViewLectureMaterial.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewLectureMaterial.adapter = LmsLectureMaterialAdapter(datas)
        binding.recyclerViewLectureMaterial.addItemDecoration(DividerItemDecoration(this,
        LinearLayoutManager.VERTICAL))
    }
}



class LmsLectureMaterial(
    val title:String,
    val subtitle: String,
    //val isChecked: Boolean
){}

class LmsLectureMaterialAdapter(val datas: MutableList<LmsLectureMaterial>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class LmsLectureMaterialViewHolder(val binding: LmsLecturematerialDataBinding): RecyclerView.ViewHolder(binding.root)
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LmsLectureMaterialViewHolder(LmsLecturematerialDataBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as LmsLectureMaterialViewHolder).binding

        binding.lectureMaterialItemTitle.text = datas[position].title
        binding.lectureMaterialItemSubtitle.text = datas[position].subtitle
        //binding.lectureMaterialItemIschecked.setImageDrawable()

        binding.lectureMaterialItemRoot.setOnClickListener {
            Log.d("LmsLectureMaterial", "item root click : $position")        }
    }
}