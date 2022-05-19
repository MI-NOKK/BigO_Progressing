package com.example.bigo.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bigo.databinding.ActivityP21newaccountBinding
import com.google.firebase.auth.FirebaseAuth

class P_2_1newaccount : AppCompatActivity() {

    private lateinit var binding: ActivityP21newaccountBinding

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityP21newaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account In...")
        progressDialog.setCanceledOnTouchOutside(false)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.next22.setOnClickListener {
            validateData()
        }
        binding.back21.setOnClickListener {
            val intent = Intent(this, P_2_Login::class.java)
            startActivity(intent)
        }
    }

    private fun validateData() {
        //get data
        email = binding.email.text.toString().trim()
        password = binding.editPw.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.email.error = "올바른 이메일 형식이 아닙니다"
        }
        else if(TextUtils.isEmpty(password)){
            //password isn't entered
            binding.editPw.error = "비밀번호를 입력 해주세요"
        }
        else if (password.length <6) {
            binding.editPw.error = "비밀번호는 6자 이상으로 입력해주세요"
        }
        else{
            //data is valid, continue signup
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //signup success
                progressDialog.dismiss()
                //get current user
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email

                //open profile
                startActivity(Intent(this,P_2_3newac::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                //signup failed
                progressDialog.dismiss()
                Toast.makeText(this, "[회원가입 실패] 이미 사용중인 이메일 입니다", Toast.LENGTH_SHORT).show()
            }
    }
}