package com.example.ch3_5

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.ch3_5.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    private lateinit var auth : FirebaseAuth
    private lateinit var callbackManager : CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)*/

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        // FirebaseAuth.getInstance()
        // 이런식으롭 불러도되지만 자바방식이라서 그냥 밑에코드로함 (둘은 같은 코드임)
        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()

        initLoginButton()
        initSignUpButton()
        initEmailAndPasswordEditText()
        initFacebookLoginButton()

    }

    private fun initLoginButton() {
        val loginButton = binding.loginButton
        loginButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            // 로그인 -> 인자값 ( 이메일, 패스워드 )
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        handleSuccessLogin()
                    }else{
                        Toast.makeText(this,"로그인에 실패했습니다",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun initSignUpButton() {
        val signUpButton = binding.signUpButton
        signUpButton.setOnClickListener {
            val email = getInputEmail()
            val password = getInputPassword()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        Toast.makeText(this,"회원가입 성공",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"회원가입 실패",Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    private fun initEmailAndPasswordEditText(){
        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText
        val loginButton = binding.loginButton
        val signUpButton = binding.signUpButton

        emailEditText.addTextChangedListener{
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }

        passwordEditText.addTextChangedListener{
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable
        }
    }

    private fun initFacebookLoginButton(){
        val facebookLoginButton = binding.facebookLoginButton

        // 실제로 버튼을 눌렸을 때 요청하는 것 (유저에게 받아올 정보)
        facebookLoginButton.setPermissions("email", "public_profile")
        facebookLoginButton.registerCallback(callbackManager, object: FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult) {
                // 로그인이 성공적
                val credential = FacebookAuthProvider.getCredential(result.accessToken.token)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this@LoginActivity){ task->
                        if(task.isSuccessful){
                            handleSuccessLogin()
                        }else{

                        }
                    }
            }

            override fun onCancel() {
                // 로그인 진행중 취소
            }

            override fun onError(error: FacebookException?) {
                // 에러발생
                Toast.makeText(this@LoginActivity,"페이스북 로그인이 실패함",Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getInputEmail() : String {
        return binding.emailEditText.text.toString()
    }

    private fun getInputPassword(): String{
        return binding.passwordEditText.text.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode,resultCode,data)
    }

    private fun handleSuccessLogin(){
        if(auth.currentUser == null){
            Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = auth.currentUser?.uid.orEmpty()
        // reference -> 최상위, 이후 child 로 단계를 낮춤
        val currentUserDB = Firebase.database.reference.child("Users").child(userId)
        val user = mutableMapOf<String,Any>()
        user["userId"] = userId

        // Users -> userId -> user 저장
        currentUserDB.updateChildren(user)

        finish()
    }

}