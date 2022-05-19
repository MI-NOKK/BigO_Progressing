package com.example.bigo.Sql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bigo.databinding.ActivityP81SqlTestQBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class P8_1_sqlTestQ : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP81SqlTestQBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var qustion = ""
        var example1 = ""
        var example2 = ""
        var example3 = ""
        var example4 = ""

        val inputStream = assets.open("sqlQ.json")
        val br = BufferedReader(InputStreamReader(inputStream))
        val str = br.readText()
        val jo = JSONObject(str)
        val ja = jo.getJSONArray("problems")
        for (i in 0 until ja.length()) {
            val iObject = ja.getJSONObject(i)
            qustion = iObject.getString("question")
            example1 = iObject.getString("example1")
            example2 = iObject.getString("example2")
            example3 = iObject.getString("example3")
            example4 = iObject.getString("example4")
        }


        binding.textView1.setText(qustion)
        binding.button8.setText(example1)
        binding.editText2.setText(example2)
        binding.editText3.setText(example3)
        binding.button4.setText(example4)

        binding.result.setOnClickListener {
            val intent = Intent(this, P8_4_sqlSol::class.java)
            startActivity(intent)
        }
    }
}