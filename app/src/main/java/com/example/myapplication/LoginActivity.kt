package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val id = findViewById<EditText>(R.id.etv_id)
        val pw = findViewById<EditText>(R.id.etv_password)
        val login = findViewById<Button>(R.id.btn_login)
        val signup = findViewById<Button>(R.id.btn_signup)

        // 회원가입 페이지로 이동하는 버튼
        signup.setOnClickListener {
            val signupIntent = Intent(this@LoginActivity, SignActivity::class.java) // 인텐트: 회원가입 페이지
            startActivity(signupIntent)
            right()    // animation
        }

        // 로그인 버튼
        login.setOnClickListener {
            if (id.text.isBlank() || pw.text.isBlank()) {   // 문자열이 없거나, 공백으로 이루어진 경우
                Toast.makeText(this@LoginActivity, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else if (id.text.isNotBlank() && pw.text.isNotBlank()) {  // text가 전부 채워져 있는 경우
                    if (userId.contains(id.text.toString())) {  // user data의 id와 입력한 id값 비교
                        identifyId = true
                    }
                    if (userPw.contains(pw.text.toString())) {  // user data의 pw와 입력한 pw값 비교
                        identifyPw = true
                    }
                }
                if (identifyId && identifyPw) {  // id,pw값 회원가입 정보와 모두 같은 경우
                    Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "정보를 다시 입력하거나 회원가입을 완료해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }





/*
<merge 후 추가구현 사항>





// 프로필페이지 로그인시 이름 받아오기

val getData = intent.getStringExtra("myname")
□.setText(getData)

// 프로필 페이지에서 회원탈퇴 버튼 누를 시 계정 삭제후, 메인페이지로 이동

btn_delete.setOnclickListner {
    removeMember()
    finish()
}

*/





