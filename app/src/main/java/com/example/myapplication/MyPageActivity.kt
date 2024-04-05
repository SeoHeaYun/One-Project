package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myapplication.MemberManger.userMap
import java.io.File
import java.util.Locale

class MyPageActivity : AppCompatActivity() {
    private lateinit var profileImage: ImageView
    private lateinit var loginInfo: String
    private lateinit var korean: RadioButton
    private lateinit var english: RadioButton
    private lateinit var languageCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        // 좌측 상단 home 버튼 클릭
        myPageBtn = findViewById(R.id.btn_home)
        myPageBtn.setOnClickListener {
            homeIntent = Intent(this@MyPageActivity, MainActivity::class.java)
            homeIntent.putExtra("loginInfo", loginInfo)
            startActivity(homeIntent)
            finish()
            left()
        }

        // 로그인 정보로 프로필 정보 가져오기
        loginInfo = intent.getStringExtra("loginInfo").toString()
        val name = findViewById<EditText>(R.id.content_name)
        val mbti = findViewById<EditText>(R.id.content_mbti)
        val thoughts = findViewById<EditText>(R.id.content_thoughts)

        name.setText(userMap[loginInfo]?.userName)
        mbti.setText(userMap[loginInfo]?.userMbti)
        thoughts.setText(userMap[loginInfo]?.userThoughts)
        profileImage = findViewById(R.id.img_profile)

        // 프로필 이미지가 없으면, 기본이미지 출력
        val myProfileImage = userMap[loginInfo]?.profile
        if (myProfileImage == null) {
            profileImage.setImageResource(R.drawable.defaultprofile)
        }
        else{
            Glide.with(this).load(myProfileImage).into(profileImage)
        }

        // 첫번째 게시글의 이름, 이미지, 글
        val userName1 = findViewById<TextView>(R.id.userName1)
        val postImage1 = findViewById<ImageView>(R.id.post_image1)
        val postWriting1 = findViewById<EditText>(R.id.post_writing1)

        userName1.text = loginInfo
        userMap[loginInfo]?.postImage?.let { postImage1.setImageResource(it[0]) }
        postWriting1.setText(userMap[loginInfo]?.postWriting?.get(0))

        // 두번째 게시글의 이름, 이미지, 글
        val userName2 = findViewById<TextView>(R.id.userName2)
        val postImage2 = findViewById<ImageView>(R.id.post_image2)
        val postWriting2 = findViewById<EditText>(R.id.post_writing2)

        userName2.text = loginInfo
        userMap[loginInfo]?.postImage?.let { postImage2.setImageResource(it[1]) }
        postWriting2.setText(userMap[loginInfo]?.postWriting?.get(1))

        // 수정하기 버튼
        val btnRevise = findViewById<Button>(R.id.btn_revise)
        btnRevise.setOnClickListener {
            userMap[loginInfo]?.postWriting?.set(0, postWriting1.text.toString())
            userMap[loginInfo]?.postWriting?.set(1, postWriting2.text.toString())

            val userRevise = MemberManger.UserInfo(
                name.text.toString(),
                mbti.text.toString(),
                thoughts.text.toString(),
                myProfileImage,
                userMap[loginInfo]?.postImage,
                userMap[loginInfo]?.postWriting
            )
            userMap[loginInfo] = userRevise
        }

        // 다국어지원
        korean = findViewById(R.id.rb_kr)
        english = findViewById(R.id.rb_en)

        val sharedPrefernces = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPrefernces.getString("My_Lang", "")
        if (language != null) {
            Log.d("로그", "language :$language")
            languageCode = language
        }

        if(languageCode == "en" || languageCode == ""){
            english.isChecked = true
        } else {
            korean.isChecked = true
        }

        korean.setOnClickListener{
            setLocate("ko")
            recreate()
        }

        english.setOnClickListener {
            setLocate("en")
            recreate()
        }
    }

    private fun setLocate(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }
}