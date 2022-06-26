package com.example.bigo.CodingTest

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bigo.R
import com.example.bigo.coParcel
import com.example.bigo.coQParcel
import com.example.bigo.databinding.P72CotestNewBinding
import com.google.firebase.firestore.FirebaseFirestore

class cotestQ_new : AppCompatActivity() {

    val coQuestion = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P72CotestNewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var coID = intent.getParcelableArrayListExtra<coParcel>("coID")
        val coid = coID?.get(0)?.coId.toString()
        val coName = coID?.get(0)?.coQ.toString()

        coQuestion.collection("cotest").document(coid)
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

        val coQ  = arrayListOf<coQParcel>()
        binding.result.setOnClickListener {
            val intent = Intent(this, coteSol::class.java)
            coQ.clear()
            coQ.add(coQParcel(coid, coName, answer, "noob"))
            intent.putParcelableArrayListExtra("coQ", coQ)
            startActivity(intent)
        }
    }
}

