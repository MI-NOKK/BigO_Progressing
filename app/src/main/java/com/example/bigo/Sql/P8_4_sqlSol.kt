package com.example.bigo.Sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP84SqlSolBinding

class P8_4_sqlSol : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP84SqlSolBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button3.setOnClickListener {
            val intent = Intent(this, P8_sql::class.java)
            startActivity(intent)
        }
    }
}