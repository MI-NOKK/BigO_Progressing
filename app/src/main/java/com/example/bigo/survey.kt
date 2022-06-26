package com.example.bigo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bigo.MainPage.Navi
import com.example.bigo.databinding.P3SurbayBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class survey : AppCompatActivity() {

    private lateinit var binding: P3SurbayBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var grade = ""
    var a = -1; var b = -1; var c = -1; var d = -1; var e = -1; var f = -1; var g = -1
    var score = 0
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P3SurbayBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.yes1.setOnClickListener {
            binding.yes1.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.no1.setBackgroundResource(R.drawable.btn_surbay)
            if (a != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            a = 0
            a += 2
        }
        binding.no1.setOnClickListener {
            binding.no1.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.yes1.setBackgroundResource(R.drawable.btn_surbay)
            if (a != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            a = 0
        }
        binding.yes2.setOnClickListener {
            binding.yes2.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.no2.setBackgroundResource(R.drawable.btn_surbay)
            if (b != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            b = 0
            b += 3
        }
        binding.no2.setOnClickListener {
            binding.no2.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.yes2.setBackgroundResource(R.drawable.btn_surbay)
            if (b != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            b = 0
        }
        binding.yes3.setOnClickListener {
            binding.yes3.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.no3.setBackgroundResource(R.drawable.btn_surbay)
            if (c != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            c = 0
            c += 2
        }
        binding.no3.setOnClickListener {
            binding.no3.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.yes3.setBackgroundResource(R.drawable.btn_surbay)
            if (c != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            c = 0
        }
        binding.yes4.setOnClickListener {
            binding.yes4.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.no4.setBackgroundResource(R.drawable.btn_surbay)
            if (d != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            d = 0
            d += 1
        }
        binding.no4.setOnClickListener {
            binding.no4.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.yes4.setBackgroundResource(R.drawable.btn_surbay)
            if (d != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            d = 0
        }
        binding.yes5.setOnClickListener {
            binding.yes5.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.no5.setBackgroundResource(R.drawable.btn_surbay)
            if (e != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            e = 0
            e += 1
        }
        binding.no5.setOnClickListener {
            binding.no5.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.yes5.setBackgroundResource(R.drawable.btn_surbay)
            if (e != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            e = 0
        }
        binding.yes6.setOnClickListener {
            binding.yes6.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.no6.setBackgroundResource(R.drawable.btn_surbay)
            if (f != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            f = 0
            f += 4
        }
        binding.no6.setOnClickListener {
            binding.no6.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.yes6.setBackgroundResource(R.drawable.btn_surbay)
            if (f != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            f = 0
        }
        binding.yes7.setOnClickListener {
            binding.yes7.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.no7.setBackgroundResource(R.drawable.btn_surbay)
            if (g != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            g = 0
            g += 4
        }
        binding.no7.setOnClickListener {
            binding.no7.setBackgroundResource(R.drawable.btn_surbay_click)
            binding.yes7.setBackgroundResource(R.drawable.btn_surbay)
            if (g != -1) {
                i -= 1
                i += 1
            }
            else {
                i += 1
            }
            g = 0
        }

        firebaseAuth = FirebaseAuth.getInstance()
        val uid = firebaseAuth.currentUser?.uid.toString()

        binding.surbayBtn.setOnClickListener {
            score = a + b + c + d + e + f + g
            when(score) {
                in 0..7 -> grade = "초급"
                in 8..12 -> grade = "중급"
                in 13..17 -> grade = "고급"
            }
            if (i == 7) {
                updateLv(uid, grade, "On")
                Toast.makeText(this,"총 점수 : $score 나의 난이도 : $grade",Toast.LENGTH_LONG).show()

                val intent = Intent(this, Navi::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this,"설문조사에 모두 참여 해주세요",Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun updateLv(uid: String, level: String, on: String){
        database = FirebaseDatabase.getInstance().getReference("Users")

        val user = mapOf<String,String>(
            "level" to level,
            "survey" to on
        )

        database.child(uid).updateChildren(user)
    }

}