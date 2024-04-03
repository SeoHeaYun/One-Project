package com.example.myapplication

// 회원 정보 값 리스트에 넣어 보관하는 싱글톤

object MemberManger {
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
        memberList.add(newMember)
    }

    fun removeMember(id: String, pw: String) {  // 회원 탈퇴버튼에서 활용할 메소드
        val iterator = memberList.iterator()    // 안전한 리스트 순회를 위해 iterator() 활용
        while (iterator.hasNext()) {
            val user = iterator.next()
            if (user.userId == id && user.userPw == pw) {
                iterator.remove()
                identifyId = false // false로 되었기 때문에 개인프로필 못들어감.
                identifyPw = false
            }
        }
    }
}

//    fun findMember(): MutableList<UserData> {    // 가입한 친구보기 (사용할지 안할지 모르겠음)
//        return memberList
//    }
//}