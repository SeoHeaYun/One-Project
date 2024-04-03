package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

    //게시글부분
        //게시글 글 더보기 관련
        var long01 = findViewById<TextView>(R.id.long_text01)
        var short01 = findViewById<TextView>(R.id.ddd01)
        var long02 = findViewById<TextView>(R.id.long_text02)
        var short02 = findViewById<TextView>(R.id.ddd02)
        var long03 = findViewById<TextView>(R.id.long_text03)
        var short03 = findViewById<TextView>(R.id.ddd03)
        var long04 = findViewById<TextView>(R.id.long_text04)
        var short04 = findViewById<TextView>(R.id.ddd04)

        var id01 = findViewById<TextView>(R.id.top_id01)
        id01.setOnClickListener {
            var intent = Intent(this,DetailPageActivity::class.java)
            intent.putExtra("userId",id01.text.toString())
            startActivity(intent)
        }

        //더보기 기능 실행
        setViewMore(long01, short01)
        setViewMore(long02, short02)
        setViewMore(long03, short03)
        setViewMore(long04, short04)
    }
    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        pickImageLauncher.launch(gallery)
    }

    //더보기 기능
    //getEllipsisCount : 해당라인의 생략된 길이를 반환함.
    fun setViewMore(contentTextView: TextView, viewMoreTextView: TextView) {
        contentTextView.post {
            val lineCount = contentTextView.layout.lineCount
            if (lineCount > 0) {
                if (contentTextView.layout.getEllipsisCount(lineCount - 1) > 0) {
                    viewMoreTextView.visibility = View.VISIBLE
                    viewMoreTextView.setOnClickListener {
                        contentTextView.maxLines = Int.MAX_VALUE
                        viewMoreTextView.visibility = View.GONE
                    }
                }
            }
        }
    }
}