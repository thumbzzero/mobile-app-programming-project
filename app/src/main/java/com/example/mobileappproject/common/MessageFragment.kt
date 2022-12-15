package com.example.mobileappproject.common

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileappproject.DBManager.DBManager
import com.example.mobileappproject.databinding.FragmentMessageBinding
import com.example.mobileappproject.databinding.ItemMessageDataBinding
import com.example.mobileappproject.databinding.LmsLecturematerialDataBinding


class MessageFragment : Fragment() {
    lateinit var binding: FragmentMessageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageBinding.inflate(inflater,container,false)

        val datas = mutableListOf<Message_data>()

        val recieveStudentSID = Student.stdInfo!!.sid
        val messages = DBManager.getSTDMESSAGE(recieveStudentSID)

        val sendStudentName = DBManager.getSTDINFO(recieveStudentSID).name

        for(i in messages){
            datas.add(Message_data("${i.content}", "보낸이 : ${sendStudentName}"))
        }

        binding.recyclerViewMessage.adapter = MessageAdapter(datas)
        binding.recyclerViewMessage.layoutManager = LinearLayoutManager(inflater.context)
        binding.recyclerViewMessage.addItemDecoration(DividerItemDecoration(inflater.context,
            LinearLayoutManager.VERTICAL))
        return binding.root
    }
}

class Message_data(
    val message_content: String,
    val sndStrudent: String
)

class MessageAdapter(val datas: MutableList<Message_data>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    class MessageViewHolder(val binding: ItemMessageDataBinding): RecyclerView.ViewHolder(binding.root)
    override fun getItemCount(): Int = datas.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MessageViewHolder(ItemMessageDataBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MessageViewHolder).binding

        binding.messageItemTitle.text = datas[position].message_content
        binding.messageItemSubtitle.text = datas[position].sndStrudent
        //binding.lectureMaterialItemIschecked.setImageDrawable()

        binding.messageItemRoot.setOnClickListener {
            Log.d("Message", "item root click : $position")        }
    }

}

