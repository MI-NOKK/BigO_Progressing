package com.example.bigo.licence

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bigo.databinding.P93LicenseQuestionBinding
import com.example.bigo.lcAnswerParcel
import com.example.bigo.lcidParcel
import com.google.firebase.firestore.FirebaseFirestore


class licenceQ : AppCompatActivity() {

    val lcQuestion = FirebaseFirestore.getInstance()
    val itemList = arrayListOf<licenceQData>()
    val adapter = licenceQAdapter(itemList)
    val lastLicence = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = P93LicenseQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var lcidList = intent.getParcelableArrayListExtra<lcidParcel>("lcidList")
        val dateId = lcidList?.get(0)?.dateId.toString()
        val lcId = lcidList?.get(0)?.lcId.toString()
        lastLicence.add(dateId)
        lastLicence.add(lcId)

        binding.recycleGichul.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleGichul.adapter = adapter

        lcQuestion.collection("licence").document(lcId)
            .collection("Date").document(dateId)
            .collection("question")
            .get().addOnSuccessListener { document ->
                itemList.clear()
                for (dc in document) {
                    val item = licenceQData(
                        dc["question"] as? String?,
                        dc["example1"] as? String?,
                        dc["example2"] as? String?,
                        dc["example3"] as? String?,
                        dc["example4"] as? String?
                    )
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        var answer = Array<String>(20, { "" })
        answer = adapter.getAnswer()
        var lcAnswer = arrayListOf<lcAnswerParcel>()
        var nextIntent = Intent(this, licenceSol::class.java)
        binding.result.setOnClickListener {
            lcAnswer.add(lcAnswerParcel(dateId, lcId, answer))
            nextIntent.putParcelableArrayListExtra("lcAnswer", lcAnswer)
            startActivity(nextIntent)
            //Toast.makeText(this, "정답을 전부 체크하지 않았습니다 ${check}", Toast.LENGTH_SHORT).show()
        }
    }

    fun getLastLicence(): List<String> {
        return lastLicence
    }

}