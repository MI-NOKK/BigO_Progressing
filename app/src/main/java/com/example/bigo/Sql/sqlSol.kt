package com.example.bigo.Sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.P85SqlSolutionBinding
import com.example.bigo.sqlQParcel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class sqlSol : AppCompatActivity() {

    val sqlSol = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P85SqlSolutionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var sqlQ = intent.getParcelableArrayListExtra<sqlQParcel>("sqlQ")
        val sqlid = sqlQ?.get(0)?.sqlId.toString()
        val sqlName = sqlQ?.get(0)?.sqlQ.toString()
        val answer = sqlQ?.get(0)?.answer.toString()
        val level = sqlQ?.get(0)?.level.toString()

        sqlSol.collection("sql").document(sqlid)
            .collection(level).document("q")
            .get().addOnSuccessListener { document->
                val correct = document["correct"].toString()

                if(correct == answer){
                    binding.tvCheck.text = "정답입니다."
                }else{
                    binding.tvCheck.text = "오답입니다."
                }
                binding.tvExplanation.text = document["explanation"].toString()

            }

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid.toString()
        binding.checkQ.setOnClickListener {
            updateCheck(uid, sqlid, sqlName)
        }

        binding.goSql.setOnClickListener {
            val intent = Intent(this, sql::class.java)
            startActivity(intent)
        }
    }

    private fun updateCheck(uid: String, sqlId: String, sqlName: String) {
        val check = sqlSol.collection("check")

        val user = mapOf<String, String>(
            "sqlId" to sqlId,
            "sqlName" to sqlName
        )

        check.document(uid).collection("sql").document(sqlId).set(user)
    }

    
}