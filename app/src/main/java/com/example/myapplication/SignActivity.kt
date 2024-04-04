package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.MemberManger.memberList
import java.util.regex.Pattern


class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val signupname = findViewById<EditText>(R.id.etv_signupname)
        val signupid = findViewById<EditText>(R.id.etv_signupid)
        val signuppw = findViewById<EditText>(R.id.etv_signuppw)
        val signup = findViewById<Button>(R.id.btn_realsignup)
        val doublecheck = findViewById<Button>(R.id.btn_double_check)
        val getUserId = MemberManger.getUserIdList()

        // 좌측 상단 home 버튼 클릭
        myPageBtn = findViewById(R.id.btn_home)
        myPageBtn.setOnClickListener {
            homeIntent = Intent(this@SignActivity, MainActivity::class.java)
            startActivity(homeIntent)
            left()
        }

        // 아이디 중복체크 버튼
        doublecheck.setOnClickListener {
            if (getUserId.contains(signupid.text.toString())) {
                Toast.makeText(this@SignActivity,"이미 사용 중인 아이디입니다.",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SignActivity,"사용 가능한 아이디입니다.",Toast.LENGTH_SHORT).show()
            }
        }

        // 회원가입 버튼
        signup.setOnClickListener {
            if (signupname.text.isBlank() || signupid.text.isBlank() || signuppw.text.isBlank()) {
                Toast.makeText(this@SignActivity, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                MemberManger.addMember(signupid.text.toString(), signuppw.text.toString())   // 회원 추가
                Toast.makeText(this@SignActivity, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // 비밀번호 입력 버튼: TextWatcher을 사용하여 실시간으로 유효성 검사
        signuppw.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isRegularPw()
            }

            private fun isRegularPw() {
                val inPutPw = signuppw.text.toString().trim()
                val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,16}$" // REGEX 패턴: 영문, 비밀번호, 특수문자 포함하여 8자리 이상 16자리 이하여야함.
                val pattern = Pattern.matches(pwPattern, inPutPw)
                if (pattern) {
                    signuppw.setBackgroundResource(R.drawable.round)  // 패턴과 일치하는 경우, 회색테두리
                } else {
                    signuppw.setBackgroundResource(R.drawable.regex_bgr) // 패턴과 일치하지 않는 경우, 빨강 테두리
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    // 뒤로가기 버튼 눌렀을 때, animation
    override fun onBackPressed() {
        super.onBackPressed()
        left()
    }
}
