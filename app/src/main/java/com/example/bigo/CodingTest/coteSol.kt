package com.example.bigo.CodingTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.coQParcel
import com.example.bigo.databinding.P75CotestSolutionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class coteSol : AppCompatActivity() {

    val coSol = FirebaseFirestore.getInstance()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P75CotestSolutionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var coQ = intent.getParcelableArrayListExtra<coQParcel>("coQ")
        val coid = coQ?.get(0)?.coId.toString()
        val coName = coQ?.get(0)?.coQ.toString()
        val answer = coQ?.get(0)?.answer.toString()
        val level = coQ?.get(0)?.level.toString()

        coSol.collection("cotest").document(coid)
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
            updateCheck(uid, coid, coName)
        }

        binding.goCote.setOnClickListener {
            val intent = Intent(this, cotest::class.java)
            startActivity(intent)
        }
    }


    private fun updateCheck(uid: String, coId: String, coName: String) {
        val check = coSol.collection("check")

        val user = mapOf<String, String>(
            "coId" to coId,
            "coName" to coName
        )

        check.document(uid).collection("cotest").document(coId).set(user)
    }

}