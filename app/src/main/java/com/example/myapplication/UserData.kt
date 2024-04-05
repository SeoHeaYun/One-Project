package com.example.myapplication

import android.net.Uri

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
            postImage = mutableListOf<Int>(R.drawable.user3_post1, R.drawable.user3_post1),
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
        var user1postImage = mutableListOf(R.drawable.user1_post1, R.drawable.user1_post2)
        var user2postImage = mutableListOf(R.drawable.user2_post1)
        var user3postImage = mutableListOf(R.drawable.user3_post1, R.drawable.user3_post2)
        var user4postImage = mutableListOf(R.drawable.user4_post1)

        var user1postWriting = mutableListOf("안녕하세요", "다들 좋은 하루 보내세요")
        var user2postWriting = mutableListOf("안녕하세요", "다들 좋은 하루 보내세요")
        var user3postWriting = mutableListOf("안녕하세요", "다들 좋은 하루 보내세요")
        var user4postWriting = mutableListOf("안녕하세요")

        var user1 = UserData("bandal04", "")
        var user2 = UserData("서해윤", "")
        var user3 = UserData("bonggyulim", "123456**")
        var user4 = UserData("장혜정", "")

        memberList.add(user1)
        memberList.add(user2)
        memberList.add(user3)
        memberList.add(user4)

        var user1Info = UserInfo("강현정", "ISTP", "팀원분들 모두 친절하시고 적극적이어서 첫 팀 프로젝트를 잘 마무리할 수 있었습니다.", null, user1postImage, user1postWriting)
        var user2Info = UserInfo("서해윤", "MBTI", "소감", null, user2postImage, user2postWriting)
        var user3Info = UserInfo("임봉규", "INTP", "안녕하세요", null, user3postImage, user3postWriting)
        var user4Info = UserInfo("장혜정", "MBTI", "소감", null, user4postImage, user4postWriting)


        userMap[user1.userId] = user1Info
        userMap[user2.userId] = user2Info
        userMap[user3.userId] = user3Info
        userMap[user4.userId] = user4Info
    }
}
//    fun removeMember(id: String, pw: String) {  // 회원 탈퇴버튼에서 활용할 메소드
//        val iterator = memberList.iterator()    // 안전한 리스트 순회를 위해 iterator() 활용
//        while (iterator.hasNext()) {
//            val user = iterator.next()
//            if (user.userId == id && user.userPw == pw) {
//                iterator.remove()
//                identifyId = false // false로 되었기 때문에 개인프로필 못들어감.
//                identifyPw = false
//            }
//        }
//    }
//}

//    fun findMember(): MutableList<UserData> {    // 가입한 친구보기 (사용할지 안할지 모르겠음)
//        return memberList
//    }
//}

