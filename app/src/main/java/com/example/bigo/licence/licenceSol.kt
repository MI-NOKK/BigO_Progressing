package com.example.bigo.licence

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP93GichulSolBinding
import com.example.bigo.testParcel
import com.google.firebase.firestore.FirebaseFirestore

class licenceSol : AppCompatActivity() {
    val lcQuestion = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP93GichulSolBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var lcAnswer = intent.getParcelableArrayListExtra<testParcel>("lcAnswer")
        var answer = Array<String>(20,{""})
        answer = lcAnswer?.get(0)?.answer!!

        var answer1 : String = ""
        for(i in 0..19){
            if(i == 0){
                answer1 = answer[0]
            }else if(i == 1){
              answer1 = answer1 + answer[1]
             }else{
                answer1 = answer1 + answer[i]
            }
        }
        binding.textView4.text = answer1

        binding.button3.setOnClickListener {
            val intent = Intent(this, licence::class.java)
            startActivity(intent)
        }
    }
}