package com.example.bigo.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bigo.databinding.P22NewaccountBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class newaccount : AppCompatActivity() {

    private lateinit var binding: P22NewaccountBinding

    private var foreceResendingToken: PhoneAuthProvider.ForceResendingToken? = null

    private var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null

    private var mVerificationId: String? = null

    private val TAG = "MAIN_TAG"

    private lateinit var progressDialog: ProgressDialog

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var database : DatabaseReference
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = P22NewaccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLl.visibility = View.VISIBLE
        binding.codeLl.visibility = View.GONE

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setCanceledOnTouchOutside(false)

        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }
            override fun onVerificationFailed(e: FirebaseException) {
                progressDialog.dismiss()
                Toast.makeText(this@newaccount, "인증실패", Toast.LENGTH_SHORT).show()
            }
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG, "onCodeSent: $verificationId")
                mVerificationId = verificationId
                foreceResendingToken = token
                progressDialog.dismiss()

                binding.phoneLl.visibility = View.VISIBLE
                binding.codeLl.visibility = View.VISIBLE
                Toast.makeText(this@newaccount, "인증번호 전송", Toast.LENGTH_SHORT).show()
            }
        }

        binding.back.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }

        binding.phoneContinueBtn.setOnClickListener {
            val phone = binding.phoneEt.text.toString().trim()

            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this@newaccount, "핸드폰 번호를 입력 해주세요", Toast.LENGTH_SHORT).show()
            }
            else{

                startPhoneNumberVerification(phone)
            }
        }

        binding.resendCodeTv.setOnClickListener {
            val phone = binding.phoneEt.text.toString().trim()

            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this@newaccount, "핸드폰 번호를 입력 해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                resendVerificationCode(phone, foreceResendingToken)
            }
        }

        binding.codeContinueBtn.setOnClickListener {
            val code = binding.codeEt.text.toString().trim()
            if (TextUtils.isEmpty(code)){
                Toast.makeText(this@newaccount, "인증코드를 입력 해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                verifyPhoneNumberWithCode(mVerificationId, code)
            }
        }

        binding.newaccountBnt.setOnClickListener{
            emptyuserinfo()
        }

    }

    fun phoneNumber82(msg : String) : String{
        val firstNumber : String = msg.substring(0,3)
        var phoneEdit = msg.substring(3)

        when(firstNumber){
            "010" -> phoneEdit = "+8210$phoneEdit"
            "011" -> phoneEdit = "+8211$phoneEdit"
            "016" -> phoneEdit = "+8216$phoneEdit"
            "017" -> phoneEdit = "+8217$phoneEdit"
            "018" -> phoneEdit = "+8218$phoneEdit"
            "019" -> phoneEdit = "+8219$phoneEdit"
            "106" -> phoneEdit = "+82106$phoneEdit"
        }
        Log.d("국가코드로 변경된 번호 ",phoneEdit)
        return phoneEdit
    }

    private fun startPhoneNumberVerification(phone: String){
        progressDialog.setMessage("핸드폰 인증 하는중...")
        progressDialog.show()

        val options = mCallBacks?.let {
            PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber82(phone))
                .setTimeout(60,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(it)
                .build()
        }
        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    }

    private fun resendVerificationCode(phone: String, token: PhoneAuthProvider.ForceResendingToken?){
        progressDialog.setMessage("인증번호 재전송...")
        progressDialog.show()

        val options = mCallBacks?.let {
            PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(it)
                .setForceResendingToken(token!!)
                .build()
        }

        if (options != null) {
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
        progressDialog.dismiss()
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code:String){
        Log.d(TAG, "verifyPhoneNumberWithCode: $verificationId $code")


        val credential = PhoneAuthProvider.getCredential(verificationId.toString(), code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        progressDialog.setMessage("인증")

        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "인증성공!", Toast.LENGTH_SHORT).show()
                firebaseAuth.signOut()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "인증실패!", Toast.LENGTH_SHORT).show()
            }

    }

    private fun emptyuserinfo() {
        val name = binding.Name.text.toString().trim()
        val nickname = binding.nickName.text.toString().trim()
        val age = binding.age.text.toString().trim()
        val phoneet = binding.phoneEt.toString().trim()
        val code = binding.codeEt.toString().trim()

        if (TextUtils.isEmpty(name)){
            binding.Name.error = "이름을 입력해주세요"
        }else if (TextUtils.isEmpty(nickname)) {
            binding.nickName.error = "닉네임을 입력해주세요"
        }else if (TextUtils.isEmpty(age)) {
            binding.age.error = "나이를 입력해주세요"
        }else if (!binding.man.isChecked && !binding.woman.isChecked) {
            Toast.makeText(this@newaccount, "성별을 선택해주세요", Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(phoneet)){
            binding.phoneEt.error = "핸드폰 번호를 입력해주세요."
        }else if (TextUtils.isEmpty(code)){
            binding.codeEt.error = "인증코드를 입력해주세요"
        }else {
            validateData()
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
            binding.editPw.error = "비밀번호는 6자 이상 입력해주세요"
        }
        else{
            //data is valid, continue signup
            firebaseSignUp()
        }
    }
    private fun sendEmailVerification()  {
        val user = Firebase.auth.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    Log.d(TAG,"Email sent.")
                }
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

                sendEmailVerification()
                firebaseUserData()
                Toast.makeText(this,"환영합니다", Toast.LENGTH_SHORT).show()


                //open profile
                startActivity(Intent(this,Login::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                //signup failed
                progressDialog.dismiss()
                Toast.makeText(this, "[회원가입 실패] 이미 사용중인 이메일 입니다", Toast.LENGTH_SHORT).show()
            }
    }

    private fun firebaseUserData() {

        val email = binding.email.text.toString()
        val name = binding.Name.text.toString()
        val nickname = binding.nickName.text.toString()
        val age = binding.age.text.toString()
        val phone = binding.phoneEt.text.toString()
        val gender = if(binding.man.isChecked) {
            binding.man.text.toString()
        }
        else {
            binding.woman.text.toString()
        }
        val UID = firebaseAuth.currentUser?.uid.toString()
        var survey = "off"
        var dateId = "0"
        var lcId = "0"
        var coStep = "0"
        var sqlStep = "0"
        var level = "0"


        database = FirebaseDatabase.getInstance().getReference("Users")
        val User = User(email, name, nickname, age, gender, phone, survey, UID, dateId, lcId, coStep, sqlStep, level )

        database.child(UID).setValue(User).addOnSuccessListener {
            binding.email.text.clear()
            binding.Name.text.clear()
            binding.nickName.text.clear()
            binding.age.text.clear()
            binding.phoneEt.text.clear()

        }
    }
}


