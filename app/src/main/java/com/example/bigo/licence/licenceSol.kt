package com.example.bigo.licence

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bigo.databinding.P94LicenseSolutionBinding
import com.example.bigo.lcAnswerParcel
import com.google.firebase.firestore.FirebaseFirestore

class licenceSol : AppCompatActivity() {
    val lcQuestion = FirebaseFirestore.getInstance()
    val lcSol = arrayListOf<licenceSolData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P94LicenseSolutionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var userAnswer = intent.getParcelableArrayListExtra<lcAnswerParcel>("lcAnswer")
        val dateId = userAnswer?.get(0)?.dateId.toString()
        val lcId = userAnswer?.get(0)?.lcId.toString()
        var answer = Array<String>(20, { "" })
        answer = userAnswer?.get(0)?.answer!!
        var check = Array<String>(20, { "" })
        var score = 0
        var btnCheck = 0

        lcQuestion.collection("licence").document(lcId)
            .collection("Date").document(dateId)
            .collection("question")
            .get().addOnSuccessListener { document ->
                lcSol.clear()
                for (dc in document) {
                    val item = licenceSolData(
                        dc["question"] as? String?,
                        dc["explanation"] as? String?,
                        dc["correct"] as? String?,
                    )
                    lcSol.add(item)
                }
                for (i in 0..19) {
                    if (answer[i] == lcSol.get(i).correct) {
                        check[i] = "정답입니다!!\n" +
                                "정답: ${lcSol.get(i).correct}, 체크한 답: ${answer[i]}"
                        score += 5
                    } else {
                        check[i] = "오답입니다!!\n" +
                                "정답: ${lcSol.get(i).correct}, 체크한 답: ${answer[i]}"
                    }
                }
                binding.solution.text =
                    "당신의 점수: ${score}\n\n${lcSol.get(0).question}\n\n${check[0]}\n\n${lcSol.get(0).explation}"
                binding.backEx.setOnClickListener {
                    btnCheck -= 1
                    if (btnCheck < 0) {
                        binding.backEx.isEnabled = false
                        btnCheck += 1
                        binding.backEx.isEnabled = true
                    } else {
                        binding.solution.text =
                            "당신의 점수: ${score}\n\n${lcSol.get(btnCheck).question}\n\n${check[btnCheck]}\n\n${lcSol.get(btnCheck).explation}"
                    }
                }
                binding.nextEx.setOnClickListener {
                    btnCheck += 1
                    if (btnCheck > 19) {
                        binding.backEx.isEnabled = false
                        btnCheck -= 1
                        binding.backEx.isEnabled = true
                    } else {
                        binding.solution.text =
                            "당신의 점수: ${score}\n\n${lcSol.get(btnCheck).question}\n\n${check[btnCheck]}\n\n${lcSol.get(btnCheck).explation}"
                    }
                }
                //adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        binding.goLicenseList.setOnClickListener {
            val intent = Intent(this, licence::class.java)
            startActivity(intent)
        }
    }
}

