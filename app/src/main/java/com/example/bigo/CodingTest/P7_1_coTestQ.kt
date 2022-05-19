package com.example.bigo.CodingTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP71CoTestQBinding

class P7_1_coTestQ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP71CoTestQBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.result.setOnClickListener {
            val intent = Intent(this, P7_4_coteSol::class.java)
            startActivity(intent)
        }
    }
}