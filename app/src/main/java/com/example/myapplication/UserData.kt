package com.example.myapplication

import android.net.Uri

var userMap= mutableMapOf<String, UserInfo>()
data class UserData(
    val id: String,
    val pw: String?
)

data class UserInfo(
    var userName: String,
    var userMbti: String,
    var userThoughts: String,
    var profile: Uri? = null,
    var postImage: MutableList<Int>?,
    var postWriting: MutableList<String>?
)
fun init() {
    var user1postImage = mutableListOf(R.drawable.user1_post1)
    var user2postImage = mutableListOf(R.drawable.user2_post1)
    var user3postImage = mutableListOf(R.drawable.user3_post1, R.drawable.user3_post2)
    var user4postImage = mutableListOf(R.drawable.user4_post1)

    var user1postWriting = mutableListOf("안녕하세요")
    var user2postWriting = mutableListOf("안녕하세요")
    var user3postWriting = mutableListOf("안녕하세요", "다들 좋은 하루 보내세요")
    var user4postWriting = mutableListOf("안녕하세요")

    var user1 = UserData("강현정", "")
    var user2 = UserData("서해윤", "")
    var user3 = UserData("bonggyulim", "")
    var user4 = UserData("장혜정", "")

    var user1Info = UserInfo("강현정", "MBTI", "소감", null, user1postImage, user1postWriting)
    var user2Info = UserInfo("서해윤", "MBTI", "소감", null, user2postImage, user2postWriting)
    var user3Info = UserInfo("임봉규", "INTP", "안녕하세요", null, user3postImage, user3postWriting)
    var user4Info = UserInfo("장혜정", "MBTI", "소감", null, user4postImage, user4postWriting)


    userMap[user1.id] = user1Info
    userMap[user2.id] = user2Info
    userMap[user3.id] = user3Info
    userMap[user4.id] = user4Info
}