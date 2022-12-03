package com.example.mobileappproject.common

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.mobileappproject.DBManager.DBConnectManager
import com.example.mobileappproject.DBManager.DBManager
import com.example.mobileappproject.DBManager.DBManager.getSTDINFO
import com.example.mobileappproject.DataManager.LoginData
import com.example.mobileappproject.R
import com.example.mobileappproject.databinding.ActivityLoginBinding
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class LoginActivity : AppCompatActivity() {
    var backBtnTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.findIdTxt.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://appfn.knu.ac.kr/login.knu?agentId=5#link-find-loginid"))
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val id = binding.idTxt.text.toString()
            val pw = binding.passwordTxt.text.toString()

            LoginData.id = id
            LoginData.pw = pw

            val temp = DBManager.getSTDINFO(id, pw)
            if(temp.name!="null"){//로그인 성공시 , 로컬저장소에 파일로 id,pw저장
                val filepath = filesDir.path + "/loginInfo.txt"
                Log.d("test",filepath)
                var file = File(filepath)

                if(!file.exists())
                    file.createNewFile()
                var fileWriter = FileWriter(file,false)
                val bufferWriter = BufferedWriter(fileWriter)
                bufferWriter.append(id)
                bufferWriter.newLine()
                bufferWriter.append(pw)
                Log.d("test","id=$id pw=$pw")
                bufferWriter.close()

                Student.stdInfo=temp
                Student.stdCourseInfo=DBManager.getSTDCOS(Student.stdInfo.sid)

                finish()
                overridePendingTransition(R.anim.none,R.anim.none)
            }else{
                Toast.makeText(this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginGuestTxt.setOnClickListener {
            //todo Login(id=guest,null)

            finish()
            overridePendingTransition(R.anim.none,R.anim.none)
        }

    }
    override fun onBackPressed() {
        if(System.currentTimeMillis()-backBtnTime > 3000){
            Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
            backBtnTime = System.currentTimeMillis()
            return
        }

        super.onBackPressed()
    }
}