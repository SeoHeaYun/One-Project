package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (userMap.isEmpty()){
            init()
        }

        val btn = findViewById<Button>(R.id.button2)
        btn.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.putExtra("loginInfo", "bonggyulim")
            startActivity(intent)
        }

        val btn2 = findViewById<Button>(R.id.button)
        btn2.setOnClickListener {
            val intent = Intent(this, DetailPageActivity::class.java)
            intent.putExtra("userId", "bonggyulim")
            startActivity(intent)
        }


    }
}