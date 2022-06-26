package com.example.bigo.login

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.bigo.R
import com.example.bigo.databinding.P21LoginBinding
import com.example.bigo.survey
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception


class Login : AppCompatActivity() {

    private lateinit var binding: P21LoginBinding

    private lateinit var progressDialog: ProgressDialog

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    private var email = ""
    private var password = ""

    private companion object{
        private const val RC_SIGN_IN = 100
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = P21LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.googleLogin.setOnClickListener{
            Log.d(TAG, "onCreate: begin Google Sign")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("BigO 로그인")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        //checkUser()

        //handle click, open register activity
        binding.newaccount.setOnClickListener {
            startActivity(Intent(this, newaccount::class.java))
        }

        //handle click, begin login
        binding.loginBtn.setOnClickListener{
            //before loggin in, validate data
            validateData()
        }
        binding.findpw.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("비밀번호 찾기")
            val view : View = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
            val username :EditText = view.findViewById<EditText>(R.id.et_username)
            builder.setView(view)
            builder.setPositiveButton("전송",DialogInterface.OnClickListener { _, _ ->
                forgotPassword(username)
            })
            builder.setNegativeButton("닫기",DialogInterface.OnClickListener { _, _  ->  })
            builder.show()
        }
    }

    private fun forgotPassword(username : EditText) {
        if (username.text.toString().isEmpty()) {
            Toast.makeText(this,"이메일 주소를 입력해주세요",Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            return
        }
        firebaseAuth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"이메일 전송",Toast.LENGTH_SHORT).show()
                }
            }

    }


    override fun onBackPressed() {
        //super.onBackPressed()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)
            }
            catch (e: Exception){
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: begin firebase auth google account")
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->

                Log.d(TAG, "firebaseAuthWithGoogleAccount: firebaseAuthWithGoogleAccount")

                val firebaseUser = firebaseAuth.currentUser

                val uid = firebaseAuth.uid
                val gmail = firebaseUser!!.email

                Log.d(TAG, "firebaseAuthWithGoogleAccount: uid: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Gmail: $gmail")

                if (authResult.additionalUserInfo!!.isNewUser){
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account created...\n $email")
                    Toast.makeText(this@Login, "계정 생성", Toast.LENGTH_SHORT).show()
                }
                else{
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \n$email")
                    Toast.makeText(this@Login, "Google 로그인", Toast.LENGTH_SHORT).show()

                }

                startActivity(Intent(this@Login, survey::class.java))
                finish()

            }
            .addOnFailureListener{e->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
                Toast.makeText(this@Login, "로그인 실패${e.message}", Toast.LENGTH_SHORT).show()
            }



    }
    private fun validateData() {
        //get data
        email = binding.editid.text.toString().trim()
        password = binding.editpw.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.editid.error = "이메일 주소를 입력해주세요"
        }
        else if (TextUtils.isEmpty(password)){
            //no password entered
            binding.editpw.error = "비밀번호를 입력해주세요"
        }
        else {
            //data is validated, begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email

                //open profile
                startActivity(Intent(this, survey::class.java))
                finish()
            }
            .addOnFailureListener { e->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this, "[로그인 실패] 이메일과 비밀번호를 확인 해주세요", Toast.LENGTH_SHORT).show()
            }
    }

    /*private fun checkUser() {
        // if user is already logged in go to P3_surbay
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser !=null){
            startActivity(Intent(this, P3_surbay::class.java))
            finish()
        }
    }*/
}
