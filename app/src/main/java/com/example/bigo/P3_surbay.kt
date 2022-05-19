package com.example.bigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bigo.MainPage.P4_Navi
import com.example.bigo.databinding.ActivityP3SurbayBinding

class P3_surbay : AppCompatActivity() {

    private lateinit var binding: ActivityP3SurbayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityP3SurbayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var score = 0
        var grade = ""

        binding.yes1.setOnClickListener {
            score += 2
            binding.yes1.isEnabled = false
            binding.no1.isEnabled = false
        }
        binding.no1.setOnClickListener {
            score += 0
            binding.yes1.isEnabled = false
            binding.no1.isEnabled = false
        }
        binding.yes2.setOnClickListener {
            score += 3
            binding.yes2.isEnabled = false
            binding.no2.isEnabled = false
        }
        binding.no2.setOnClickListener {
            score += 0
            binding.yes2.isEnabled = false
            binding.no2.isEnabled = false
        }
        binding.yes3.setOnClickListener {
            score += 2
            binding.yes3.isEnabled = false
            binding.no3.isEnabled = false
        }
        binding.no3.setOnClickListener {
            score += 0
            binding.yes3.isEnabled = false
            binding.no3.isEnabled = false
        }
        binding.yes4.setOnClickListener {
            score += 1
            binding.yes4.isEnabled = false
            binding.no4.isEnabled = false
        }
        binding.no4.setOnClickListener {
            score += 0
            binding.yes4.isEnabled = false
            binding.no4.isEnabled = false
        }
        binding.yes5.setOnClickListener {
            score += 1
            binding.yes5.isEnabled = false
            binding.no5.isEnabled = false
        }
        binding.no5.setOnClickListener {
            score += 0
            binding.yes5.isEnabled = false
            binding.no5.isEnabled = false
        }
        binding.yes6.setOnClickListener {
            score += 4
            binding.yes6.isEnabled = false
            binding.no6.isEnabled = false
        }
        binding.no6.setOnClickListener {
            score += 0
            binding.yes6.isEnabled = false
            binding.no6.isEnabled = false
        }
        binding.yes7.setOnClickListener {
            score += 4
            binding.yes7.isEnabled = false
            binding.no7.isEnabled = false
        }
        binding.no7.setOnClickListener {
            score += 0
            binding.yes7.isEnabled = false
            binding.no7.isEnabled = false
        }

        binding.next4.setOnClickListener {
            when(score) {
                in 0..7 -> grade = "초급"
                in 8..12 -> grade = "중급"
                in 13..17 -> grade = "고급"
            }

            Toast.makeText(this,"총 점수 : $score 나의 난이도 : $grade",Toast.LENGTH_LONG).show()

            val intent = Intent(this, P4_Navi::class.java)
            startActivity(intent)
        }

    }
}