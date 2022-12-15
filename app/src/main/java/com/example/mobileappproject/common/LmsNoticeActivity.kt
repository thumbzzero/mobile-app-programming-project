package com.example.mobileappproject.common


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.DBManager.DBManager
import com.example.mobileappproject.DBManager.DB_dc_sub_noti
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityLmsNoticeBinding
import com.example.mobileappproject.databinding.LmsNoticeDataBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class LmsNoticeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityLmsNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerViewDay = binding.recyclerViewDay
        val recyclerViewWeek = binding.recyclerViewWeek
        val recyclerViewMonth = binding.recyclerViewMonth
        recyclerViewDay.layoutManager=LinearLayoutManager(this)
        recyclerViewWeek.layoutManager=LinearLayoutManager(this)
        recyclerViewMonth.layoutManager=LinearLayoutManager(this)


        val datasDay = mutableListOf<LmsNotice>()
        val datasWeek = mutableListOf<LmsNotice>()
        val datasMonth = mutableListOf<LmsNotice>()

        val subjcetCode = intent.getStringExtra("course_code")

        //todo 데이터 db에서 동적으로 가져오기
        val list = DBManager.getSUBNOTI(subjcetCode!!)

        val now = LocalDate.now()

        for(item in list){
            val day=LocalDate.parse(item.date, DateTimeFormatter.ISO_DATE)
            Log.d("test","오늘날짜$now ,공지날짜$day")
            when{
                now.isEqual(day) ->{
                    datasDay.add(LmsNotice(item.title,item.written,item.cont,item.date))
                }
                now.minusDays(7).isBefore(day)->{
                    datasWeek.add(LmsNotice(item.title,item.written,item.cont,item.date))
                }
                else-> {
                    datasMonth.add(LmsNotice(item.title, item.written, item.cont, item.date))
                }
            }
        }
        if(datasDay.isEmpty()) binding.foldd.visibility=View.GONE
        if(datasWeek.isEmpty()) binding.foldw.visibility=View.GONE
        if(datasMonth.isEmpty()) binding.foldm.visibility=View.GONE

        //--------------



        val adapterDay = LmsNoticeAdapter(datasDay)
        val adapterWeek = LmsNoticeAdapter(datasWeek)
        val adapterMonth = LmsNoticeAdapter(datasMonth)

        recyclerViewDay.adapter=adapterDay
        recyclerViewWeek.adapter=adapterWeek
        recyclerViewMonth.adapter=adapterMonth


        
        

        binding.foldd.setOnClickListener(MySetOnClickListener(recyclerViewDay))
        binding.foldm.setOnClickListener(MySetOnClickListener(recyclerViewMonth))
        binding.foldw.setOnClickListener(MySetOnClickListener(recyclerViewWeek))
        
    }

}

class MySetOnClickListener(val recyclerView: RecyclerView): View.OnClickListener{
    override fun onClick(p0: View?) {
        val temp: ImageView = p0 as ImageView
        when(recyclerView.visibility){
            View.VISIBLE -> {
                recyclerView.visibility=View.GONE
                temp.setImageResource(R.drawable.unfold)
            }
            View.GONE -> {
                recyclerView.visibility=View.VISIBLE
                temp.setImageResource(R.drawable.fold)
            }
        }
    }
}




class LmsNotice(
    val title:String,
    val writter:String,
    val mainTxt:String,
    val date:String
){}


class LmsNoticeViewHolder(val binding: LmsNoticeDataBinding) : RecyclerView.ViewHolder(binding.root)

class LmsNoticeAdapter(val datas: MutableList<LmsNotice>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        LmsNoticeViewHolder(
            LmsNoticeDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as LmsNoticeViewHolder).binding
        binding.title.text = datas[position].title
        binding.date.text = datas[position].date
        binding.mainTxt.text=datas[position].mainTxt


        binding.data.setOnClickListener {
            //itemClickListener.OnClick(it, position)
        }
    }


    interface OnItemClickListener {
        fun OnClick(v: View, position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
