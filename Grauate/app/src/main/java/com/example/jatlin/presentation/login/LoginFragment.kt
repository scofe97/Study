package com.example.jatlin.presentation.login

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jatlin.R
import com.example.jatlin.databinding.FragmentLoginBinding
import com.example.jatlin.extensions.toGone
import com.example.jatlin.extensions.toVisible
import com.example.jatlin.extensions.toast
import com.example.jatlin.presentation.profile.ProfileContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.scope.ScopeFragment
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import java.util.regex.Pattern


class LoginFragment : ScopeFragment(), LoginContract.View {

    companion object {
        const val TAG = "LoginFragment"
    }

    private lateinit var binding: FragmentLoginBinding
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override val presenter: LoginContract.Presenter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    override fun initViews() {
        with(binding) {

            tilInputEmailEditText.editText?.addTextChangedListener {
                val enable = validateEmail() && validatePassword()
                emailLoginButton.isEnabled = enable
                signUpButton.isEnabled = enable
            }

            tilInputPwEditText.editText?.addTextChangedListener {
                val enable = validateEmail() && validatePassword()
                emailLoginButton.isEnabled = enable
                signUpButton.isEnabled = enable
            }


            emailLoginButton.setOnClickListener {
                emailLogin()
            }

            signUpButton.setOnClickListener {
                emailSignUp()
            }

            googleLoginButton.setOnClickListener {
                googleLogin()
            }
        }
    }

    private fun validateEmail(): Boolean{
        val value: String = binding.tilInputEmailEditText.editText?.text.toString()
        return if (value.isEmpty()) {
            binding.tilInputEmailEditText.error = "이메일을 입력해주세요."
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            binding.tilInputEmailEditText.error = "이메일 형식이 잘 못 되었습니다."
            false
        } else {
            binding.tilInputEmailEditText.error = null
            binding.tilInputEmailEditText.isErrorEnabled = false
            true
        }
    }

    private fun validatePassword() : Boolean{
        val value: String = binding.tilInputPwEditText.editText?.text.toString()
        return if (value.isEmpty()) {
            binding.tilInputPwEditText.error = "비밀번호를 입력해주세요."
            false
        } else if (!Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{6,20}$", value)) {
            binding.tilInputPwEditText.error = "비밀번호 형식이 잘 못 되었습니다."
            false
        } else {
            binding.tilInputPwEditText.error = null
            binding.tilInputPwEditText.isErrorEnabled = false
            true
        }
    }

    override fun showLoadingIndicator() {
        binding.progressBar.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.toGone()
    }

    override fun showDescription(message: String) {
        requireActivity().toast(message)
    }

    override fun showLog(message: String) {
        Log.d(TAG,message)
    }

    override fun navigateProfileFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
    }


    // 구글 로그인 함수 모음
    private val gso: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("40835889833-82sgnvd8v8g1b9nnsnklohqe5b2dmju8.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }

    private val gsc by lazy { GoogleSignIn.getClient(requireActivity(), gso) }


    // 인텐트 결과값 받아옴 ( 구글 정보)
    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    task.getResult(ApiException::class.java)?.let { account ->
                        showLog("구글로그인 성공 : ${account.idToken}")
                        firebaseAuthWithGoogle(account.idToken!!)
                    } ?: throw Exception()

                } catch (e: Exception) {
                    e.printStackTrace()
                    showLog("구글로그인 실패 : $e ")

                }
            }
        }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    showLog("구글 증명 : 성공")
                    presenter.createUserDB(auth.currentUser!!)
                    navigateProfileFragment()
                } else {
                    showLog("구글 증명 : 실패")
                }
            }
    }

    private fun googleLogin() {
        val signIntent = gsc.signInIntent
        loginLauncher.launch(signIntent)
    }

    private fun emailLogin() {
        val email = binding.tilInputEmailEditText.editText?.text.toString()
        val password = binding.tilInputPwEditText.editText?.text.toString()

        //로그인
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    requireActivity().toast("로그인에 성공")
                    navigateProfileFragment()
                } else {
                    requireActivity().toast("로그인에 실패")
                }
            }
    }

    private fun emailSignUp() {
        val email = binding.tilInputEmailEditText.editText?.text.toString()
        val password = binding.tilInputPwEditText.editText?.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    requireActivity().toast("회원가입에 성공했습니다. 로그인 버튼을 눌러주세요")
                    presenter.createUserDB(auth.currentUser!!)
                } else {
                    requireActivity().toast("회원가입에 실패했습니다. 이미 가입한 이메일일 수 있습니다.")
                }

            }
    }


}