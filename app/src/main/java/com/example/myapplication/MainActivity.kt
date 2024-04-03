package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.pm.PackageManager
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat

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
        // 마이페이지 버튼 클릭 시
        myPageBtn = findViewById(R.id.btn_mypage)
        myPageBtn.setOnClickListener {
            //로그인 O, 마이페이지
            //로그인 X, 로그인페이지
            /*if () {
                val intent = Intent(this@MainActivity, MyPageActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@MainActivity, SignInActivity::class.java)
                startActivity(intent)
            }*/
        }

        //프로필 사진 이미지뷰 클릭 시
        ivCamera = findViewById(R.id.Iv_camera)
        ivCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }
    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
    }
}