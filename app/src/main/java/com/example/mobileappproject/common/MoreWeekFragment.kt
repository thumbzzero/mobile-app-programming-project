package com.example.mobileappproject.common

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toIcon
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.R
import com.example.mobileappproject.data.*
import com.example.mobileappproject.databinding.FragmentMoreWeekBinding
import com.example.mobileappproject.databinding.NoticeDataBinding
import com.example.mobileappproject.databinding.WeekDataBinding
import org.jsoup.Jsoup
import kotlin.concurrent.thread


class MoreWeekFragment : Fragment() {
    val KNU:List<String> = listOf("https://www.knu.ac.kr/wbbs/wbbs/bbs/btin/list.action?bbs_cde=28&menu_idx=214",
        "https://www.knu.ac.kr/wbbs/wbbs/user/extNews/list.action?menu_idx=216")
    val KNUNAME:List<String> = listOf("포토뉴스","언론으로본 KNU")

    val datas = mutableListOf<WeekInfo>()
    lateinit var recyclerView:RecyclerView
    val adapter:WeekAdapter = WeekAdapter(datas)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoreWeekBinding.inflate(inflater,container,false)



        val layoutManager = LinearLayoutManager(activity)

        recyclerView = binding.recyclerViewWeek
        recyclerView.layoutManager=layoutManager

        adapter.setItemClickListener(object : WeekAdapter.OnItemClickListener {
            override fun OnClick(v: View, position: Int) {
                val data = datas[position]
                val intent = Intent(Intent.ACTION_VIEW,Uri.parse(data.site_address))
                startActivity(intent)
            }
        })
        recyclerView.adapter=adapter




        showDB()

        findWeekLocal()





        return binding.root
    }

    fun findWeekLocal(){

        thread {
            val dbHelper = DBHelper(context,"localDB.db",null,1)
            val db = dbHelper.writableDatabase
            val sql ="insert into WeekInfo (title, date, site, views, site_address,main_text) values (?, ?, ?, ?, ?, ?)"
            try {

                val doc = Jsoup.connect(KNU[0])
                    .get()
                val element = doc.select("div.gallery_list li")
                for (elem in element){
                    val title = elem.select("dt a").text()
                    val pageUrl = elem.select("dt a").attr("abs:href")
                    val date = elem.select("dd.gallery_day_view").text()
                    val views = "조회수임시"
                    val site = KNUNAME[0]
                    val main_text=elem.select("dd.gallery_txt_view").text()
                    val arg = arrayOf<String>(title,date,site,views,pageUrl,main_text)
                    Log.d("test","$arg")
                    try {
                        db.execSQL(sql,arg)
                    }catch (e:Exception){
                    }

                }

                val doc_ = Jsoup.connect(KNU[1])
                    .get()
                val element_ = doc_.select("tbody tr")
                for (elem in element_){
                    val title = elem.select("td.subject").text()
                    val pageUrl = elem.select("a").attr("abs:href")
                    val date = elem.select("td.date").text()
                    val views = elem.select("td.hit").text()
                    val site = KNUNAME[1]
                    val main_text="메인텍스트"
                    val arg = arrayOf<String>(title,date,site,views,pageUrl,main_text)
                    try {
                        db.execSQL(sql,arg)
                    }catch (e:Exception){
                    }

                }






                db.close()

            }catch (e:Exception){ }
            activity?.runOnUiThread {
                showDB()
            }
        }

    }
    fun showDB(){
        datas.clear()



        val dbHelper = DBHelper(context,"localDB.db",null,1)
        val db = dbHelper.writableDatabase

        //val sql ="SELECT * FROM NoticeInfo ORDER BY date DESC, views DESC"

        var targetSite=""
        if(true){//학과에 따라서 공지가져오기
            for(it in KNUNAME){
                targetSite+="'"+it+"',"
            }
        }
        targetSite+="'temp'"
        val sql ="SELECT * FROM WeekInfo WHERE SITE IN ($targetSite)ORDER BY date DESC, views DESC"
        val c =db.rawQuery(sql,null)

        while(c.moveToNext()){
            val title_pos=c.getColumnIndex("title")
            val date_pos = c.getColumnIndex("date")
            val site_pos = c.getColumnIndex("site")
            val views_pos = c.getColumnIndex("views")
            val site_address_pos = c.getColumnIndex("site_address")
            val main_text_pos = c.getColumnIndex("main_text")

            val title =c.getString(title_pos)
            val date = c.getString(date_pos)
            val site=c.getString(site_pos)
            val views=c.getString(views_pos)
            val site_address=c.getString(site_address_pos)
            val main_text = c.getString(main_text_pos)
            datas.add(WeekInfo(title,date,site,views,site_address,main_text))

        }

        db.close()


        adapter.notifyDataSetChanged()
    }

}




class WeekViewHolder(val binding: WeekDataBinding) : RecyclerView.ViewHolder(binding.root)

class WeekAdapter(val datas: MutableList<WeekInfo>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = WeekViewHolder(WeekDataBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as WeekViewHolder).binding
        binding.weekSite.text=datas[position].site
        binding.weekTitle.text=datas[position].title
        binding.weekDate.text=datas[position].date
        binding.weekView.text=datas[position].views
        binding.mainText.text=datas[position].main_text
        if(!datas[position].main_text.equals("메인텍스트")) binding.siteBtn.visibility=View.VISIBLE
        if(datas[position].views.equals("조회수임시")) binding.siteView.visibility=View.INVISIBLE
        binding.siteBtn.setOnClickListener {
            when(binding.mainText.visibility){
                View.VISIBLE-> {
                    binding.mainText.visibility = View.GONE
                    binding.siteBtn.setImageResource(R.drawable.unfold)
                }
                View.GONE->{
                    binding.mainText.visibility=View.VISIBLE
                    binding.siteBtn.setImageResource(R.drawable.fold)
                }
            }
        }
        binding.mainText.setOnClickListener {
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