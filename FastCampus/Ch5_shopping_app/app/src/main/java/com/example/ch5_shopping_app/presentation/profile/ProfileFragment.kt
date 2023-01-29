package com.example.ch5_shopping_app.presentation.profile

import android.app.Activity
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import com.example.ch5_shopping_app.R
import com.example.ch5_shopping_app.databinding.FragmentProfileBinding
import com.example.ch5_shopping_app.extensions.loadCenterCrop
import com.example.ch5_shopping_app.extensions.toast
import com.example.ch5_shopping_app.presentation.BaseFragment
import com.example.ch5_shopping_app.presentation.adapter.ProductListAdapter
import com.example.ch5_shopping_app.presentation.detail.ProductDetailActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import org.koin.android.ext.android.inject
import java.lang.Exception

internal class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {

    companion object{
        const val TAG = "ProfileFragment"
    }

    override val viewModel  by inject<ProfileViewModel>()

    override fun getViewBinding(): FragmentProfileBinding =
        FragmentProfileBinding.inflate(layoutInflater)

    private val adapter = ProductListAdapter()

    private val gso : GoogleSignInOptions by lazy{
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    private val gsc by lazy { GoogleSignIn.getClient(requireActivity(), gso) }

    private val firebaseAuth by lazy{ FirebaseAuth.getInstance() }

    private val loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d(TAG, "loginLauncher 실행")
        Log.d(TAG, "${result.resultCode == Activity.RESULT_OK}")
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                task.getResult(ApiException::class.java)?.let { account ->
                    Log.d(TAG, "firebaseAuthWithGoogle : ${account.id}")
                    viewModel.saveToken(account.idToken?: throw Exception())
                } ?: throw Exception()
            }catch (e: Exception){
                e.printStackTrace()
                handleErrorState()
            }
        }
    }

    override fun observeData() = viewModel.profileStateLiveData.observe(this) {
        Log.d(TAG, "state : $it")
        when(it){
            is ProfileState.Uninitialized -> initViews()
            is ProfileState.Loading -> handleLoadingState()
            is ProfileState.Login -> handleLoginState(it)
            is ProfileState.Success -> handleSuccessState(it)
            is ProfileState.Error -> handleErrorState()
            else -> {}
        }
    }

    private fun initViews() = with(binding){
        Log.d(TAG, "initViews")
        recyclerView.adapter = adapter
        loginButton.setOnClickListener{
            signInGoogle()
        }

        logoutButton.setOnClickListener{
            viewModel.signOut()
        }
    }

    private fun handleLoadingState() = with(binding){
        Log.d(TAG, "Loading")
        progressBar.isVisible = true
        loginRequireGroup.isGone = true
    }

    private fun signInGoogle(){
        Log.d(TAG, "로그인 버튼 클릭")
        val signIntent = gsc.signInIntent
        loginLauncher.launch(signIntent)

        // 런쳐
    }

    private fun handleSuccessState(state : ProfileState.Success) = with(binding){
        Log.d(TAG, "handleSuccessState")
        progressBar.isGone = true

        when(state){
            is ProfileState.Success.Registered -> {
                handleRegisteredState(state)
            }
            is ProfileState.Success.NotRegistered -> {
                profileGroup.isGone = true
                loginRequireGroup.isVisible = true
            }
        }
    }

    private fun handleLoginState(state : ProfileState.Login) = with(binding){
        progressBar.isVisible = true
        val credential  = GoogleAuthProvider.getCredential(state.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) {task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    viewModel.setUserInfo(user)
                }else{
                    viewModel.setUserInfo(null)
                }
            }
    }

    private fun handleRegisteredState( state : ProfileState.Success.Registered) = with(binding){
        profileGroup.isVisible = true
        loginRequireGroup.isGone = true
        profileImageView.loadCenterCrop(state.profileImageUri.toString(), 60f)
        userNameTextView.text = state.userName

        if(state.productList.isEmpty()){
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        }else{
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setProductList(state.productList){
                startActivity(
                    ProductDetailActivity.newIntent(requireContext(), it.id)
                )
            }
        }
    }

    private fun handleErrorState(){
        requireContext().toast("에러가 발생")
    }
}