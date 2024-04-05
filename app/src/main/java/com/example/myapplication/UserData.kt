package com.example.myapplication

import android.net.Uri
import java.util.Queue

// 회원 정보 값 리스트에 넣어 보관하는 싱글톤
object MemberManger {
    // 회원가입 데이터
    data class UserData(val userId: String, val userPw: String)

    val memberList = mutableListOf<UserData>()  // 회원의 id와 pw를 한 쌍으로 보관하는 리스트

    fun getUserIdList(): List<String> {         // 위 memberList의 (id,pw) 한쌍에서 Id값만 리스트로 추출
        return memberList.map { it.userId }
    }

    fun getUserPwList(): List<String> {            //  위 memberList의 (id,pw) 한쌍에서 Pw값만 리스트로 추출
        return memberList.map { it.userPw }
    }

    fun addMember(id: String, pw: String) {    // 회원가입 버튼에서 활용할 메소드.
        val newMember = UserData(id, pw)
        val newUserInfo = UserInfo(
            userName = "이름입력",
            userMbti = "MBTI",
            userThoughts = "",
            profile = null,
            postImage = mutableListOf<Int>(R.drawable.post_new, R.drawable.post_user1),
            postWriting = mutableListOf("안녕하세요", "좋은하루보내세요")
        )
        memberList.add(newMember)
        userMap[newMember.userId] = newUserInfo
    }

    var userMap= mutableMapOf<String, UserInfo>()

    // 데이터 뿌리는 곳
    data class UserInfo(
        var userName: String,
        var userMbti: String,
        var userThoughts: String,
        var profile: Uri? = null,
        var postImage: MutableList<Int>?,
        var postWriting: MutableList<String>?
    )
    fun init() {
        var user1postImage = mutableListOf(R.drawable.post_user1, R.drawable.post_user4)
        var user2postImage = mutableListOf(R.drawable.post_user2, R.drawable.post_user3)
        var user3postImage = mutableListOf(R.drawable.post_user3, R.drawable.post_user2)
        var user4postImage = mutableListOf(R.drawable.post_user4, R.drawable.post_user1)

        var user1postWriting = mutableListOf("안녕하세요, 안드로이드 3기 강현정입니다.\n이번 원스타그램 프로젝트에서 혜정님과 함께 메인 페이지를 담당했습니다.\n여러 팀 프로젝트를 통해 개발 역량을 키우는 것이 목표입니다.", "다들 좋은 하루 보내세요")
        var user2postWriting = mutableListOf("안녕하세요! 안드로이드 3기 서해윤입니다.\n 첫 프로젝트이지만 팀원들이 함께 잘 도와준 덕분에 무사히 마무리할 수 있었습니다.\n아직 많이 부족하지만, 끈기있게 마무리하겠습니다:)", "다들 좋은 하루 보내세요")
        var user3postWriting = mutableListOf("안녕하세요 제 이름은 임봉규입니다.\n첫 팀프로젝트에 감기까지 걸려서 힘들었는데\n팀원분들 모두 열심히 하신 덕분에 잘 따라갈 수 있었습니다.", "다들 좋은 하루 보내세요")
        var user4postWriting = mutableListOf("안녕하세요 제 이름은 장혜정입니다. 저의 mbti는 isfj입니다.\n제가 가장 좋아하는 음식은 햄버거이며,\n브랜드 중에서는 버거킹을 좋아합니다.\n그리고 저의 취미생활은 풍경 사진 찍기입니다.\n밖에서 멋진 곳을 보고 사진 찍는 것이 삶의 낙인 것 같습니다.", "다들 좋은 하루 보내세요")
        var user1 = UserData("bandal03", "Aaaa123!")
        var user2 = UserData("haeyun", "haeyun00!")
        var user3 = UserData("bonggyulim", "123456**")
        var user4 = UserData("hyejeong", "aaaa1111@")

        memberList.add(user1)
        memberList.add(user2)
        memberList.add(user3)
        memberList.add(user4)

        var user1Info = UserInfo("강현정", "ISTP", "음악감상", null, user1postImage, user1postWriting)
        var user2Info = UserInfo("서해윤", "MBTI", "독서", null, user2postImage, user2postWriting)
        var user3Info = UserInfo("임봉규", "INTP", "게임", null, user3postImage, user3postWriting)
        var user4Info = UserInfo("장혜정", "ISFJ", "사진찍기", null, user4postImage, user4postWriting)


        userMap[user1.userId] = user1Info
        userMap[user2.userId] = user2Info
        userMap[user3.userId] = user3Info
        userMap[user4.userId] = user4Info
    }
}

