package com.example.bigo.Sql

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bigo.databinding.P83SqlNomalBinding
import com.example.bigo.sqlParcel
import com.example.bigo.sqlQParcel
import com.google.firebase.firestore.FirebaseFirestore

class sqlQ_normal : AppCompatActivity() {
    val sqlQuestion = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P83SqlNomalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var sqlID = intent.getParcelableArrayListExtra<sqlParcel>("sqlID")
        val sqlid = sqlID?.get(0)?.sqlId.toString()
        val sqlName = sqlID?.get(0)?.sqlQ.toString()

        sqlQuestion.collection("sql").document(sqlid)
            .collection("normal").document("q")
            .get().addOnSuccessListener { document ->
                binding.question.text = document["question"].toString()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        var answer = binding.answer.text.toString().trim()

        val sqlQ  = arrayListOf<sqlQParcel>()
        binding.result.setOnClickListener {
            val intent = Intent(this, sqlSol::class.java)
            sqlQ.clear()
            sqlQ.add(sqlQParcel(sqlid, sqlName, answer, "normal"))
            intent.putParcelableArrayListExtra("sqlQ", sqlQ)
            startActivity(intent)
        }
    }
}