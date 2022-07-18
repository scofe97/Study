package com.example.ch5_shopping_app.presentation.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ch5_shopping_app.data.preference.PreferenceManager
import com.example.ch5_shopping_app.domain.DeleteOrderedProductListUseCase
import com.example.ch5_shopping_app.domain.GetOrderedProductListUseCase
import com.example.ch5_shopping_app.presentation.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ProfileViewModel(
    private val preferenceManager: PreferenceManager,
    private val getOrderedProductListUseCase: GetOrderedProductListUseCase,
    private val deleteOrderedProductListUseCase: DeleteOrderedProductListUseCase
) : BaseViewModel() {

    companion object {
        const val TAG = "ProfileViewModel"
    }

    private val _profileStateLiveData = MutableLiveData<ProfileState>(ProfileState.Uninitialized)
    val profileStateLiveData: LiveData<ProfileState> = _profileStateLiveData


    override fun fetchData(): Job = viewModelScope.launch {

        setState(ProfileState.Loading)

        preferenceManager.getIdToken()?.let {
            setState(
                ProfileState.Login(it)
            )
        } ?: kotlin.run {
            setState(
                ProfileState.Success.NotRegistered
            )
        }

        Log.d(ProfileFragment.TAG, "state $_profileStateLiveData")

    }

    private fun setState(state: ProfileState) {
        Log.d(TAG, "state : $state")
        _profileStateLiveData.postValue(state)
    }

    fun saveToken(idToken: String) = viewModelScope.launch {
        preferenceManager.putIdToken(idToken)
        fetchData()
    }

    fun setUserInfo(firebaseUser : FirebaseUser?) = viewModelScope.launch{
        Log.d(TAG, "user : $firebaseUser")
        firebaseUser?.let { user ->
            Log.d(TAG, "user : $user")
            setState(
                ProfileState.Success.Registered(
                    user.displayName ?: "익명",
                    user.photoUrl,
                    getOrderedProductListUseCase()
                )
            )
        }
    }

    fun signOut() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            preferenceManager.removedToken()
        }
        deleteOrderedProductListUseCase()
        fetchData()
    }
}
