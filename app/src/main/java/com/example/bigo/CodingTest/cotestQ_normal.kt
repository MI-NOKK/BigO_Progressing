package com.example.bigo.CodingTest

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bigo.coParcel
import com.example.bigo.coQParcel
import com.example.bigo.databinding.P73CotestNomalBinding
import com.google.firebase.firestore.FirebaseFirestore

class cotestQ_normal : AppCompatActivity() {
    val coQuestion = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P73CotestNomalBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var coID = intent.getParcelableArrayListExtra<coParcel>("coID")
        val coid = coID?.get(0)?.coId.toString()
        val coName = coID?.get(0)?.coQ.toString()

        coQuestion.collection("cotest").document(coid)
            .collection("normal").document("q")
            .get().addOnSuccessListener { document ->
                binding.question.text = document["question"].toString()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        var answer = binding.answer.text.toString().trim()

        val coQ  = arrayListOf<coQParcel>()
        binding.result.setOnClickListener {
            val intent = Intent(this, coteSol::class.java)
            coQ.clear()
            coQ.add(coQParcel(coid, coName, answer, "normal"))
            intent.putParcelableArrayListExtra("coQ", coQ)
            startActivity(intent)
        }
    }
}