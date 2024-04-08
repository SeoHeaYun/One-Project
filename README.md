# Onestagram
인스타그램 UI 클론 프로젝트 README.md
<br/>

# 📚 목차

- [1.프로젝트 소개 및 팀플랜](#1.-프로젝트-소개-및-팀플랜)
- [2.팀 구성](#2.-팀-구성)
- [3.앱 시연 영상](#3.-앱-시연-영상)
- [4.제작과정 및 협업과정](#4.-제작과정-및-협업과정)
- [5.트러블 슈팅](#5.-트러블-슈팅)
  
# 1. 프로젝트 소개 및 팀플랜

## 프로젝트 소개
- 프로젝트 의미
> '1조'의 '첫'번째 프로젝트
- 실제 Instasgram SNS의 디자인과 기능을 차용하여 구현
- 게시물과 스토리 버튼을 통해 회원가입 된 친구 목록 및 정보 확인 가능

## 팀플랜
- 복잡한 기술보다는 팀원 간 '협업과 의사소통'에 주안점
- 코드 컨벤션
  - 해상도 통일
  - Emulator: nexus 5 api 31 통일
  - kt 파일: Camel Case / XML파일: Snake Case
  - string 파일 이용하여 text 작성
  - 주석 설명 구체적으로 달기
    

- Git 컨벤션
  - "[커밋 타입] 커밋 내용"
  - Feat: 새로운 기능 추가
  - FiX: 버그 수정
  - Update: 정상적으로 동작했지만 보완한 것
  - Simplify: refactor와 유사하지만 약한 수정, 코드 단순화
  - Design: 사용자 UI 디자인 변경시
  - etc..


# 2. 팀 구성

<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/SeoHeaYun"><img src="https://github.com/SeoHeaYun/One-Project/assets/159236003/1a2f4fb1-f445-4bf5-a1da-9453d2ec64ba"
      heigt="50px" width="50px"/><br /><sub><b>팀장: 서해윤 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/BanDalKang"><img src="https://github.com/SeoHeaYun/One-Project/assets/159236003/44e2ebc9-c476-46a4-8295-a03833d226a3" 
       heigt="50px" width="50px"/><br /><sub><b>팀원 : 강현정 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/hyezg"><img src="https://github.com/SeoHeaYun/One-Project/assets/159236003/b6f4d822-36fb-4917-ada7-a7c6325d3fdf"
       height="60px" width="50px";/><br /><sub><b>팀원: 장혜정 </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/bonggyulim"><img src="https://github.com/SeoHeaYun/One-Project/assets/159236003/076e2da2-ee9e-4dac-872b-4450cb22a9d7"
       height="60px"width="50px;" alt=""/><br /><sub><b>팀원: 임봉규 </b></sub></a><br /></td>
     <tr/>
  </tbody>
</table>
<br/>

# ⚙️ 3. 앱 시연영상
![image](https://github.com/SeoHeaYun/One-Project/assets/159236003/447b50e1-bc6d-46d7-bf86-962867e2fae6)
(https://youtu.be/3VBq5TbABWU)

# 📜 4. 제작과정 및 협업과정
## 타임라인
- 4/1: 기획
  - 요구사항 명세서 👉 구현사항 분석
  - 와이어 프레임 구상
  - 기술 스택 선정
  - 역할분배
- 4/2 ~ 4/3: 개발
  - Git과 칸반보드를 활용한 의견 문서화 및 공유
- 4/4: 디자인
  - 코드 병합 후 개선점 추가 및 UI 통일
- 4/5: MERGE
  - Main 브랜치에 merge 후 시연영상 제작
 
## 기획: 요구사항 명세서
![image](https://github.com/SeoHeaYun/One-Project/assets/159236003/c83ce2ab-476b-4bb8-a183-157208fca93c)

- 각 구현사항들을 세부적으로 나누어 분류
- 구현사항의 설명을 바탕으로 중요도 설정
- 필수구현 사항을 중점으로 와이어프레임 구상

 ## 기획: 와이어프레임(Figma)
  ![image](https://github.com/SeoHeaYun/One-Project/assets/159236003/4f66c960-51b4-45ba-ba56-a3cefbe812fb)
  
## 개발: 칸반보드 활용
![image](https://github.com/SeoHeaYun/One-Project/assets/159236003/889356c8-afa3-40a6-a853-522253aa3b7f)

- 각 요소를 칸반(Kanvan)이라고 하고, 개발 구현 상황(할 일, 진행 중, 완료)에 맞추어,  칸반을 이동
- 문서화:  각 구현을 진행하면서, 생겼던 문제와 해결 방안 그리고 참고자료 등을 해당 칸반에 기재
- 역할 분배:  각 칸반에 담당자 이름을 기재하여, 역할 분담


# ⚠️ 5. 트러블 슈팅 
> ## Git Merge 단위

- Issue
  : Git에 익숙치 않아 처음에 수시로 merge하지 않고,  Page 단위로 만든 후,  순차적으로 merge를 하였는데, 코드 충돌이 많이 났음

- Solve
  : 이후,  최소한의 기능 단위로 개발 후,  수시로 commit하는 습관을 들임

> ## 싱글톤(Singleton)의 필요성

 - Issue
   : 코틀린kt. 파일에 필요한 이미지들을 그때그때 적용하다 보니, 중복되는 경우가 발생 → 코드중복,  리소스 낭비 문제발생 인지
   
 - Solve
   :  Object 클래스를 만들어, 앱 실행 시 바로 이미지 객체를 만들고, 이 데이터들을 Map형태로 관리하여, 필요할 때 가져다 쓰기
   
 ![image](https://github.com/SeoHeaYun/One-Project/assets/159236003/c4f99b29-c2fd-4c71-bc0f-4a06781675e8)

> ## Activiyty 생명주기: 프로필 갤러리 사진 등록 이슈

- Issue
  : 싱글톤에 데이터가 잘 저장되어 있음에도, 메인페이지에서 프로필 사진을 등록한 뒤, 마이페이지로 이동했을 때, 선택한 이미지가 보이지 않는 상황이 발생

- Solve
  : 마이페이지가 onRestart()가 됐을 때에 프로필 이미지를 불러오는 로직이 빠져있는 걸 확인.  그 후, fun onRestart()를 override 후 로직을 추가하여 해결

![image](https://github.com/SeoHeaYun/One-Project/assets/159236003/2aa5eb0e-869c-4d2f-b19b-ceaf34d4c3dc)


