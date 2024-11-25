# BigO_Progressing

코딩 테스트와 자격증 문제를 통해 취업 준비를 효율적으로 지원하는 문제 풀이 앱입니다.

![5](https://github.com/user-attachments/assets/6bc2090f-609f-4ffd-8ecf-247324f5875a)

<aside>
🗣 캡스톤 디자인으로 진행한 프로젝트입니다. BigO는 효율성을 나타내는 big O 표기법처럼 효율성 있게 취업으로다가 갈수 있는 문제 풀이 앱입니다. 취업에 관련된 코딩테스트, SQL, 자격증 등의 문제들을모바일 환경에서 쉽고 간편하게 풀어보고 전공자 및 입문자의 실력 향상에 한 걸음 보탬이 되고자하는 어플입니다.

</aside>

### 💻사용한 기술

- Kotlin
- Firebase Auth, Firestore Database, Realtime Database

### 🧑🏻‍💻담당기능

- 기본적인 화면 구상, 아이디어, 팀원 과제 부여 및 관리
- 리사이클러뷰를 이용해 문제 리스트와 문제 페이지 개발
- Firestore Database에 문제에 관련된 데이터 관리 및 읽기

### 소스코드
 
 Firestore에서 데이터를 가져와 RecyclerView를 통해 화면에 표시하는 기능

```java
 binding.recycleLicense.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleLicense.adapter = adapter
        lcName.collection("licence")
            .get().addOnSuccessListener { document ->
                itemList.clear()
                for (dc in document) {
                    val item = licenceData(dc["lcName"] as String, dc["id"] as String)
                    itemList.add(item)
                   }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

```


Firebase 실시간 데이터베이스에서 사용자의 설문 완료 여부를 확인하고, 그에 따라 Navi 화면이나 설문 조사 화면(survey)으로 이동, 만약 사용자가 로그인하지 않은 상태라면, 로그인 화면(Login)으로 이동
```java
  database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val surveyDo = snapshot.child(uid).child("survey").value.toString()
                if (firebaseUser != null) {
                    if (surveyDo == "On") {
                        startActivity(Intent(this@StartPage, Navi::class.java))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        finish()
                    } else {
                        startActivity(Intent(this@StartPage, survey::class.java))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        finish()
                    }
                } else {
                    startActivity(Intent(this@StartPage, Login::class.java))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@StartPage, "fail data set", Toast.LENGTH_SHORT).show()
            }
        })
```

