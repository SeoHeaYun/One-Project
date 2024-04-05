package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.MemberManger.userMap


class DetailPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        val userId = intent.getStringExtra("userId")
        Log.d("userId", "userId: "+userId.toString())

        // 좌측 상단 home 버튼 클릭
        myPageBtn = findViewById(R.id.btn_home)
        myPageBtn.setOnClickListener {
            homeIntent = Intent(this@DetailPageActivity, MainActivity::class.java)
            startActivity(homeIntent)
            left()
        }

        // 유저정보 출력
        val name = findViewById<TextView>(R.id.content_name)
        val mbti = findViewById<TextView>(R.id.content_mbti)
        val thoughts = findViewById<TextView>(R.id.content_thoughts)

        name.text = userMap[userId]?.userName
        mbti.text = userMap[userId]?.userMbti
        thoughts.text = userMap[userId]?.userThoughts

        //
        val profileImage = findViewById<ImageView>(R.id.iv_profile)
        val userProfileImage = userMap[userId]?.profile
        if (userProfileImage == null) {
            profileImage.setImageResource(R.drawable.defaultprofile)
        }

        // 첫번째 게시글의 이름, 이미지, 글
        val userName1 = findViewById<TextView>(R.id.userName1)
        val postImage1 = findViewById<ImageView>(R.id.post_image1)
        val postWriting1 = findViewById<TextView>(R.id.post_writing1)

        userName1.text = userId
        userMap[userId]?.postImage?.let { postImage1.setImageResource(it.get(0)) }
        postWriting1.text = userMap[userId]?.postWriting?.get(0)

        // 두번째 게시글의 이름, 이미지, 글
        val userName2 = findViewById<TextView>(R.id.userName2)
        val postImage2 = findViewById<ImageView>(R.id.post_image2)
        val postWriting2 = findViewById<TextView>(R.id.post_writing2)

        userName2.text = userId
        userMap[userId]?.postImage?.let { postImage2.setImageResource(it.get(1)) }
        postWriting2.text = userMap[userId]?.postWriting?.get(1)
    }
}