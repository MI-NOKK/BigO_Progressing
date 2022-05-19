package com.example.bigo.CodingTest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.MainPage.P4_Navi
import com.example.bigo.databinding.ActivityP7CoTestBinding

class P7_coTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP7CoTestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button7.setOnClickListener {
            val intent = Intent(this, P4_Navi::class.java)
            startActivity(intent)
        }

        binding.Go1.setOnClickListener {
            val intent = Intent(this, P7_1_coTestQ::class.java)
            startActivity(intent)
        }
    }
}