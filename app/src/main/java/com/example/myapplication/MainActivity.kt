package com.example.myapplication

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.myapplication.MemberManger.init
import com.example.myapplication.MemberManger.userMap


val userId = MemberManger.getUserIdList()  // ctrl click 하면 함수 설명이 있습니다.
val userPw = MemberManger.getUserPwList()
var identifyId = false // 입력한 Id가 회원가입한 정보 즉, UserData에 있는 Id와 동일한지 식별
var identifyPw = false // 둘다 true 여야 마이페이지로 이동가능
class MainActivity : AppCompatActivity() {
    private lateinit var myPageBtn: ImageView
    private lateinit var ivCamera: ImageView
    private var imageUri: Uri? = null

    // 갤러리 열기
    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            }
        }
    // 선택한 사진 이미지뷰에 등록하기
    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let {
                    imageUri = it
                    ivCamera.setImageURI(imageUri)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (userMap.isEmpty()){
            init()
        }
        // 마이페이지 버튼 클릭 시`
        myPageBtn = findViewById(R.id.btn_mypage)
        myPageBtn.setOnclickListner{
            if(identifyId && identifyPw) {
                val profileIntent = Intent(this@MainActivity), MyPageActivity::class.java)     // 로그인 되어 있을 시, 내 정보 값 던지면서 개인페이지로 이동
                intent.putStringExtra("myname", userId.last().toString)
                startActivity(profileIntent)
            } else {
                Toast.makeText(this@MainActivity, "로그인 페이지로 이동합니다.", Toast.LENGTH_SHORT).show()
                val loginIntent = Intent(this@MainActivity, LoginActivity::class.java) // 로그인 안되어 있을 시, 로그인 페이지로 이동
                startActivity(loginIntent)
            }
        }

        //프로필 사진 이미지뷰 클릭 시
        ivCamera = findViewById(R.id.Iv_camera)
        ivCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }


    }
    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
    }
}