package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MemberManger.userMap
import java.util.Locale

class MyPageActivity : AppCompatActivity() {

    private lateinit var loginInfo: String
    private lateinit var korean: RadioButton
    private lateinit var english: RadioButton
    private lateinit var languageCode: String
    private lateinit var profileImage: ImageView
    private var imageUri: Uri? = null


    // 갤러리 열기
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted){
                val intent = Intent(Intent.ACTION_PICK)
                intent.setDataAndType(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "image/*"
                )
                pickImageLauncher.launch(intent)
                right()
            }
        }

    // 선택한 사진 이미지뷰에 등록하기
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let {
                    profileImage.setImageURI(it)
                    imageUri = it
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)


        // 좌측 상단 home 버튼 클릭
        myPageBtn = findViewById(R.id.btn_home)
        myPageBtn.setOnClickListener {
            homeIntent = Intent(this@MyPageActivity, MainActivity::class.java)
            startActivity(homeIntent)
            left()
        }

        // 로그인 정보로 프로필 정보 가져오기

        profileImage = findViewById(R.id.img_profile)
        val name = findViewById<EditText>(R.id.content_name)
        val mbti = findViewById<EditText>(R.id.content_mbti)
        val thoughts = findViewById<EditText>(R.id.content_thoughts)
        val userName1 = findViewById<TextView>(R.id.userName1)
        val userName2 = findViewById<TextView>(R.id.userName2)
        val postImage1 = findViewById<ImageView>(R.id.my_post_image1)
        val postImage2 = findViewById<ImageView>(R.id.my_post_image2)
        val postWriting1 = findViewById<TextView>(R.id.post_writing1)
        val postWriting2 = findViewById<TextView>(R.id.post_writing2)

        loginInfo = intent.getStringExtra("name").toString()
        when(loginInfo) {
            "강현정" -> {
                name.setText("강현정")
                mbti.setText("ISTP")
                thoughts.setText("첫 프로젝트 즐거웠습니다!")
                postImage1.setImageResource(R.drawable.user1_post2)
                postImage2.setImageResource(R.drawable.user1_post1)
            }

            "서해윤" -> {
                name.setText("서해윤")
                mbti.setText("ENFJ")
                thoughts.setText("첫 프로젝트 즐거웠습니다!")
                postImage1.setImageResource(R.drawable.top)
                postImage2.setImageResource(R.drawable.cloud)
                userName1.setText("@Westcoast_yun")
                userName2.setText("@Westcoast_yun")
                postWriting1.setText("행복한 하루!")
                postWriting2.setText("바람이 분다.")
            }

            "임봉규" -> {
                name.setText("임봉규")
                mbti.setText("INTP")
                thoughts.setText("첫 프로젝트 즐거웠습니다!")
                postImage1.setImageResource(R.drawable.user3_post1)
                postImage2.setImageResource(R.drawable.user1_post2)
            }

            "장혜정" -> {
                name.setText("강현정")
                mbti.setText("ISFJ")
                thoughts.setText("첫 프로젝트 즐거웠습니다!")
                postImage1.setImageResource(R.drawable.user4_post1)
                postImage2.setImageResource(R.drawable.user2_post1)
            }
        }



        // 수정하기 버튼
        val btnRevise = findViewById<Button>(R.id.btn_revise)
        btnRevise.setOnClickListener {
            val userRevise = MemberManger.UserInfo(
                name.text.toString(),
                mbti.text.toString(),
                thoughts.text.toString(),
                imageUri,
                userMap[loginInfo]?.postImage,
                userMap[loginInfo]?.postWriting
            )

            userMap[loginInfo] = userRevise
        }

        // 프로필 이미지가 없으면, 기본이미지 출력
        val myProfileImage = userMap[loginInfo]?.profile
        if (myProfileImage == null) {
            profileImage.setImageResource(R.drawable.defaultprofile)
        }


        // 프로필 사진 변경하는 버튼(버전에 따라 선택)
        val btnProfileChange = findViewById<Button>(R.id.btn_profile_change)
        btnProfileChange.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
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