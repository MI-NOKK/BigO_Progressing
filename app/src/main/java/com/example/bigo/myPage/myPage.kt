package com.example.bigo.myPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bigo.MainPage.Navi
import com.example.bigo.R
import com.example.bigo.databinding.P61MypageBinding
import com.example.bigo.login.Login
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class myPage : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = P61MypageBinding.inflate(inflater, container, false)
        // 1. 뷰 바인딩 설정

        fun checkemail() {
            //check user is logged in or not
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null){
                //user not null, user is logged in
                val email = firebaseUser.email
                binding.nickName.text = email
            }
            else {
                //user not null, user us logged in
                val intent = Intent(activity, Login::class.java)
                startActivity(intent)
            }

        }
        fun checkuser(){
            val User = firebaseAuth.currentUser
            if (User != null) {
                if (User.isEmailVerified) {
                    binding.verifyMsg.visibility = View.GONE
                    binding.resendCode.visibility = View.GONE
                } else {
                    binding.verifyMsg.visibility = View.VISIBLE
                    binding.verifyMsg.visibility = View.VISIBLE
                }
            }
        }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(binding.root.context, gso)

        firebaseAuth = FirebaseAuth.getInstance()
        checkemail()
        checkuser()

        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            googleSignInClient.signOut()
            checkemail()
        }

        binding.back.setOnClickListener {
            val intent = Intent(activity, Navi::class.java)
            startActivity(intent)
        }
        binding.notice.setOnClickListener {
            val intent = Intent(activity, mypageNotice::class.java)
            startActivity(intent)
        }
        binding.check.setOnClickListener {
            val intent = Intent(activity, CheckQ::class.java)
            startActivity(intent)
        }
        binding.newPw.setOnClickListener {
            sendPasswordRset()
        }
        binding.resendCode.setOnClickListener {
            val Users = firebaseAuth.currentUser
            Users!!.sendEmailVerification()
            Toast.makeText(activity,"인증메일을 보냈습니다.",Toast.LENGTH_SHORT).show()
        }

        // 2. 프래그먼트 레이아웃 뷰 반환
        return binding.root

    }

    private fun sendPasswordRset() {
        val emailAddress = firebaseAuth.currentUser?.email

        if (emailAddress != null) {
            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(activity,"변경메일을 보냈습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }




}
