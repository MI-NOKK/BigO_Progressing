package com.example.bigo.CodingTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP72CoTetQBinding

class P7_2_coTetQ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP72CoTetQBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.result.setOnClickListener {
            val intent = Intent(this, P7_4_coteSol::class.java)
            startActivity(intent)
        }
    }
}