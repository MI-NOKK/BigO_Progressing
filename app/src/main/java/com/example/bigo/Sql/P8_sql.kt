package com.example.bigo.Sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.MainPage.P4_Navi
import com.example.bigo.databinding.ActivityP8SqlBinding

class P8_sql : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP8SqlBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button7.setOnClickListener {
            val intent = Intent(this, P4_Navi::class.java)
            startActivity(intent)
        }

        binding.Go1.setOnClickListener {
            val intent = Intent(this, P8_1_sqlTestQ::class.java)
            startActivity(intent)
        }
    }
}