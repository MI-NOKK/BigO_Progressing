package com.example.bigo.login

import android.app.ProgressDialog
import android.content.ContentProviderClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.bigo.P3_surbay
import com.example.bigo.R

import com.example.bigo.databinding.ActivityP2LoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception


class P_2_Login : AppCompatActivity() {

    private lateinit var binding: ActivityP2LoginBinding

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
        binding = ActivityP2LoginBinding.inflate(layoutInflater)
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
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        //checkUser()

        //handle click, open register activity
        binding.newac.setOnClickListener {
            startActivity(Intent(this, P_2_1newaccount::class.java))
        }

        //handle click, begin login
        binding.btnLogin.setOnClickListener{
            //before loggin in, validate data
            validateData()
        }
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
                    Toast.makeText(this@P_2_Login, "Account created...\n $email", Toast.LENGTH_SHORT).show()
            }
                else{
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing user... \n$email")
                    Toast.makeText(this@P_2_Login, "LoggedIn...\n $email", Toast.LENGTH_SHORT).show()

                }

                    startActivity(Intent(this@P_2_Login, P3_surbay::class.java))
                    finish()

            } 
            .addOnFailureListener{e->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Loggin Failed due to ${e.message}")
                Toast.makeText(this@P_2_Login, "Loggin Failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }



    }

    private fun validateData() {
        //get data
        email = binding.editId.text.toString().trim()
        password = binding.editPw.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.editId.error = "이메일 주소를 입력해주세요"
        }
        else if (TextUtils.isEmpty(password)){
            //no password entered
            binding.editPw.error = "비밀번호를 입력해주세요"
        }
        else{
            //data is validated, begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email

                //open profile
                startActivity(Intent(this, P3_surbay::class.java))
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
