package com.example.myapplication

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


lateinit var myPageBtn: ImageView // 모든 페이지 좌측 상단에 있는 홈버튼
lateinit var homeIntent: Intent
var identifyId = false // ID,PW 입력값과 UserData 리스트값과 일치하는지 비교 -> 둘다 true 여야 로그인&마이페이지 진입가능
var identifyPw = false
class MainActivity : AppCompatActivity() {
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
        // 마이페이지 버튼 클릭 시
        myPageBtn = findViewById(R.id.btn_mypage)
        myPageBtn.setOnClickListener {
            if(identifyId && identifyPw) {
                val profileIntent = Intent(this@MainActivity, MyPageActivity::class.java)   // 로그인 돼 있을 경우, 마이페이지로 이동
                startActivity(profileIntent)
                right()
            } else {
                Toast.makeText(this@MainActivity, "로그인 페이지로 이동합니다.", Toast.LENGTH_SHORT).show() // 로그인 안 돼 있을 경우, 개인페이지로 이동
                val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(loginIntent)
                right()
            }
        }

        //프로필 사진 이미지뷰 클릭 시
        ivCamera = findViewById(R.id.Iv_camera)
        ivCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                right()
            }
        }
    }
    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
        right()
    }
}