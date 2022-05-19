package com.example.bigo.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.bigo.R
import com.example.bigo.databinding.ActivityP23newacBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class P_2_3newac : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityP23newacBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val adapter = ArrayAdapter.createFromResource(this,R.array.agencyList,R.layout.support_simple_spinner_dropdown_item)

        binding.back23.setOnClickListener {
            val intent = Intent(this, P_2_1newaccount::class.java)
            startActivity(intent)
        }

        binding.next2.setOnClickListener {
            val name = binding.Name.text.toString()
            val nickname = binding.nickName.text.toString()
            val age = binding.age.text.toString()
            val gender = binding.gender.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(name, nickname, age, gender)
            database.child(nickname).setValue(User).addOnSuccessListener {
                binding.Name.text.clear()
                binding.nickName.text.clear()
                binding.age.text.clear()
                binding.gender.text.clear()

                Toast.makeText(this,"환영합니다", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, P_2_Login::class.java)
                startActivity(intent)

            }.addOnFailureListener {

                Toast.makeText(this,"다시 작성해주세요", Toast.LENGTH_SHORT).show()

            }


        }


    }

}