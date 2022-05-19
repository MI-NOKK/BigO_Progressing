package com.example.bigo.myPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bigo.MainPage.P4_Navi
import com.example.bigo.R
import com.example.bigo.databinding.FragmentP6MyPageBinding
import com.example.bigo.login.P_2_Login
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class P6_myPage : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentP6MyPageBinding.inflate(inflater, container, false)
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
                val intent = Intent(activity, P_2_Login::class.java)
                startActivity(intent)
            }
        }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(binding.root.context, gso)

        firebaseAuth = FirebaseAuth.getInstance()
        checkemail()

        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            googleSignInClient.signOut()
            checkemail()
        }

        binding.back.setOnClickListener {
            val intent = Intent(activity, P4_Navi::class.java)
            startActivity(intent)
        }
        binding.notice.setOnClickListener {
            val intent = Intent(activity, p6_4_notice::class.java)
            startActivity(intent)
        }
        binding.check.setOnClickListener {
            val intent = Intent(activity, P6_1_check::class.java)
            startActivity(intent)
        }
        binding.newId.setOnClickListener {
            val intent = Intent(activity, P6_2_chI::class.java)
            startActivity(intent)
        }
        binding.newPw.setOnClickListener {
            val intent = Intent(activity, P6_3_chPW::class.java)
            startActivity(intent)
        }

        // 2. 프래그먼트 레이아웃 뷰 반환
        return binding.root

    }




}
