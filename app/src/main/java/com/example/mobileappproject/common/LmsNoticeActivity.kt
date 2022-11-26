package com.example.mobileappproject.common

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityLmsNoticeBinding
import com.example.mobileappproject.databinding.LmsNoticeDataBinding
import com.example.mobileappproject.databinding.NoticeDataBinding

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

        //todo 데이터 db에서 동적으로 가져오기
        /*
        for(i in x){
            if(오늘)datasDay.add
            elseif(이번주)datasWeek.add
        }
        */

        datasDay.add(LmsNotice("테스트1","작성자1","본문","2022/02/00"))
        datasWeek.add(LmsNotice("테스트2","작성자1","본문","2022/02/00"))
        datasMonth.add(LmsNotice("테스트3","작성자1","본문","2022/02/00"))
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
