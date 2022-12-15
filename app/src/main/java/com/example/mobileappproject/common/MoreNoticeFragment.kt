package com.example.mobileappproject.common

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.data.DBHelper
import com.example.mobileappproject.data.NoticeAdapter
import com.example.mobileappproject.data.NoticeInfo
import com.example.mobileappproject.databinding.FragmentMoreNoticeBinding
import org.jsoup.Jsoup
import kotlin.concurrent.thread


class MoreNoticeFragment : Fragment() {
    val CSE:List<String> = listOf("https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&sca=%EC%9D%BC%EB%B0%98%EA%B3%B5%EC%A7%80",
        "https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&sca=%ED%95%99%EC%82%AC",
        "https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&sca=%EC%9E%A5%ED%95%99,",
        "https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&sca=%EC%8B%AC%EC%BB%B4",
        "https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_1&sca=%EA%B8%80%EC%86%9D",
        "https://computer.knu.ac.kr/bbs/board.php?bo_table=sub5_3_a")
    val CSENAME:List<String> = listOf("컴퓨터학부-일반공지","컴퓨터학부-학사공지","컴퓨터학부-장학","컴퓨터학부-심컴",
        "컴퓨터학부-글솝","컴퓨터학부-학부인재")



    val datas = mutableListOf<NoticeInfo>()
    lateinit var recyclerView:RecyclerView
    val adapter:NoticeAdapter = NoticeAdapter(datas)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoreNoticeBinding.inflate(inflater,container,false)

        val layoutManager = LinearLayoutManager(activity)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager=layoutManager

        adapter.setItemClickListener(object : NoticeAdapter.OnItemClickListener {
            override fun OnClick(v: View, position: Int) {
                val data = datas[position]

                val intent = Intent(Intent.ACTION_VIEW,Uri.parse(data.site_address))
                startActivity(intent)
            }
        })
        recyclerView.adapter=adapter



        showDB()

        findNoticeLocal()




        return binding.root
    }



    fun findNoticeLocal(){

        thread {
            val dbHelper = DBHelper(context,"localDB.db",null,1)
            val db = dbHelper.writableDatabase
            val sql ="insert into NoticeInfo (title, date, site, views, site_address) values (?, ?, ?, ?, ?)"
            try {
                for(i in 0..CSE.size-1){
                    val doc = Jsoup.connect(CSE[i])
                        .get()
                    val element = doc.select("tbody tr")
                    for (elem in element){
                        val title = elem.select("div.bo_tit").text()
                        val pageUrl = elem.select("a").attr("abs:href")
                        val date = elem.select("td.td_datetime.hidden-xs").text()
                        val views = elem.select("td.td_num.hidden-xs").text()
                        val site = CSENAME[i]
                        val arg = arrayOf<String>(title,date,site,views,pageUrl)

                        try {
                            db.execSQL(sql,arg)
                        }catch (e:Exception){}

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
            for(it in CSENAME){
                targetSite+="'"+it+"',"
            }
        }
        targetSite+="'temp'"
        val sql ="SELECT * FROM NoticeInfo WHERE SITE IN ($targetSite)ORDER BY date DESC, views DESC"
        val c =db.rawQuery(sql,null)

        while(c.moveToNext()){
            val title_pos=c.getColumnIndex("title")
            val date_pos = c.getColumnIndex("date")
            val site_pos = c.getColumnIndex("site")
            val views_pos = c.getColumnIndex("views")
            val site_address_pos = c.getColumnIndex("site_address")

            val title =c.getString(title_pos)
            val date = c.getString(date_pos)
            val site=c.getString(site_pos)
            val views=c.getString(views_pos)
            val site_address=c.getString(site_address_pos)
            datas.add(NoticeInfo(title,date,site,views,site_address))

        }

        db.close()


        adapter.notifyDataSetChanged()
    }



}