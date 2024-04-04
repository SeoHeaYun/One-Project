package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
    private lateinit var IvDetail1: ImageView
    private lateinit var IvDetail2: ImageView
    private lateinit var IvDetail3: ImageView
    private lateinit var IvDetail4: ImageView
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
                Log.d("logC", identifyPw.toString())
                if(identifyId && identifyPw) {
                    val profileIntent = Intent(this@MainActivity, MyPageActivity::class.java)   // 로그인 되어 있을 시, 내 정보 값 던지면서 개인페이지로 이동
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


            // 유저별 스토리 클릭시
            IvDetail1 = findViewById(R.id.Iv_detail1)
            IvDetail2 = findViewById(R.id.Iv_detail2)
            IvDetail3 = findViewById(R.id.Iv_detail3)
            IvDetail4 = findViewById(R.id.Iv_detail4)

            val storyList = listOf(IvDetail1, IvDetail2, IvDetail3, IvDetail4)
            storyList.forEach { story ->
                story.setOnClickListener {
                    val detailPage = Intent(this@MainActivity, DetailPageActivity::class.java)   // 로그인 되어 있을 시, 내 정보 값 던지면서 개인페이지로 이동
                    when (story) {
                        IvDetail1 -> detailPage.putExtra("userId", "강현정")
                        IvDetail2 -> detailPage.putExtra("userId", "서해윤")
                        IvDetail3 -> detailPage.putExtra("userId", "bonggyulim")
                        IvDetail4 -> detailPage.putExtra("userId", "장혜정")
                    }
                    startActivity(detailPage)
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

            //게시글 사진위의 아이디 클릭시 디테일 페이지로 전환
            //1번


        var id01 = findViewById<TextView>(R.id.top_id01)
        var id02 = findViewById<TextView>(R.id.top_id02)
        var id03 = findViewById<TextView>(R.id.top_id03)
        var id04 = findViewById<TextView>(R.id.top_id04)

        val idList = listOf<TextView>(id01, id02, id03, id04)

        idList.forEach { detail ->
            detail.setOnClickListener {
                var intent = Intent(this, DetailPageActivity::class.java)
                intent.putExtra("userId", detail.text.toString())
                startActivity(intent)
                right()
            }
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
        right()
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