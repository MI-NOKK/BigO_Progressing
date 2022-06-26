package com.example.bigo.Sql

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bigo.*
import com.example.bigo.databinding.P82SqlNewBinding
import com.google.firebase.firestore.FirebaseFirestore

class sqlQ_new : AppCompatActivity() {

    val sqlQuestion = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P82SqlNewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var sqlID = intent.getParcelableArrayListExtra<sqlParcel>("sqlID")
        val sqlid = sqlID?.get(0)?.sqlId.toString()
        val sqlName = sqlID?.get(0)?.sqlQ.toString()

        sqlQuestion.collection("sql").document(sqlid)
            .collection("noob").document("q")
            .get().addOnSuccessListener { document ->
                binding.question.text = document["question"].toString()
                binding.example01.text = document["example01"].toString()
                binding.example02.text = document["example02"].toString()
                binding.example03.text = document["example03"].toString()
                binding.example04.text = document["example04"].toString()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        var answer = ""
        binding.example.setOnCheckedChangeListener { Radiogroup, i ->
            when (i) {
                R.id.example01 -> answer = "1"
                R.id.example02 -> answer = "2"
                R.id.example03 -> answer = "3"
                R.id.example04 -> answer = "4"
            }
        }

        val sqlQ  = arrayListOf<sqlQParcel>()
        binding.result.setOnClickListener {
            val intent = Intent(this, sqlSol::class.java)
            sqlQ.clear()
            sqlQ.add(sqlQParcel(sqlid, sqlName, answer, "noob"))
            intent.putParcelableArrayListExtra("sqlQ", sqlQ)
            startActivity(intent)
        }
    }
}

