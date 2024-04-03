package com.example.myapplication

import android.app.Activity

// animation 함수를 Activity의 확장함수로 빼놓았습니다. 필요하실 때에 자유롭게 사용하세요.
fun Activity.right() {
    overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
}

fun Activity.left() {
    overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left_exit)
}