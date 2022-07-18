package com.example.jatlin.presentation.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.jatlin.R
import com.example.jatlin.databinding.FragmentProfileBinding
import com.example.jatlin.extensions.loadCenterCrop
import com.example.jatlin.extensions.toGone
import com.example.jatlin.extensions.toVisible
import com.example.jatlin.extensions.toast
import com.example.jatlin.data.entity.UserEntity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.scope.ScopeFragment


class ProfileFragment : ScopeFragment(), ProfileContract.View {

    companion object {
        const val TAG = "ProfileFragment"
    }

    private lateinit var binding: FragmentProfileBinding
    override val presenter: ProfileContract.Presenter by inject()

    // view 가 초기화중
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    // 완전히 view 가 생성됨
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    override fun showLoadingIndicator() {
        binding.progressBar.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.toGone()
    }

    override fun showLoginState() {
        with(binding) {
            profileLoginState.toVisible()
            editUser.toGone()
            profileLogoutState.toGone()
            showLog("showLoginState 실행 : ${profileLoginState.visibility}")
        }

    }

    override fun showLoginUser(model: UserEntity?) {
        with(binding) {
            showLog("$model")
            model?.let {
                profileName.text = model.userName ?: "익명"
                profileEmail.text = model.userEmail

                var number = model.userPhoneNumber
                if (number != null) {
                    val first = number.slice(IntRange(0, 2))
                    val second = number.slice(IntRange(3, 6))
                    val last = number.slice(IntRange(7, 10))
                    val phoneText = "$first-$second-$last"
                    profilePhoneNumber.text = phoneText
                } else {
                    profilePhoneNumber.text = "번호 없음"
                }
                tilUserNameEditText.editText?.setText(model.userName)
                tilUserPhoneEditText.editText?.setText(model.userPhoneNumber)

                model.userImage?.let {
                    profileImage.loadCenterCrop(model.userImage, 60f)
                } ?: kotlin.run {
                    profileImage.setImageResource(R.drawable.basic_profile)
                }
            }
        }
    }

    override fun showLogoutState() {
        with(binding) {
            profileImage.setImageResource(R.drawable.ic_launcher_foreground)
            profileLoginState.toGone()
            editUser.toGone()
            profileLogoutState.toVisible()
            showLog("showLoginState 실행 : ${profileLoginState.visibility}")
        }
    }

    override fun showEditUserState() {
        with(binding) {
            profileLoginState.toGone()
            editUser.toVisible()
            profileLogoutState.toGone()
        }
    }

    override fun showDescription(message: String) {
        requireActivity().toast(message)
    }

    override fun showLog(message: String) {
        Log.d(TAG, message)
    }

    override fun navigateLoginFragment() {
        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
    }

    private fun initViews() {
        with(binding) {
            profileImage.clipToOutline = true
            profileLogoutButton.setOnClickListener {
                presenter.logout()
            }
            profileLoginButton.setOnClickListener {
                navigateLoginFragment()
            }
            profileInformationButton.setOnClickListener {
                showEditUserState()
            }
            userEditButton.setOnClickListener {
                val nameText = tilUserNameEditText.editText?.text.toString()
                val phoneText = tilUserPhoneEditText.editText?.text.toString()

                presenter.updateFirebaseDB(nameText, phoneText)
            }

            tilUserNameEditText.editText?.addTextChangedListener {
                val enable = validateName() && validatePhone()
                userEditButton.isEnabled = enable
            }

            tilUserPhoneEditText.editText?.addTextChangedListener {
                val enable = validateName() && validatePhone()
                userEditButton.isEnabled = enable
            }
        }
    }

    private fun validateName(): Boolean {
        val value: String = binding.tilUserNameEditText.editText?.text.toString()
        return if (value.isEmpty()) {
            binding.tilUserNameEditText.error = "이름을 입력해주세요."
            false
        } else {
            binding.tilUserNameEditText.error = null
            binding.tilUserNameEditText.isErrorEnabled = false
            true
        }
    }

    private fun validatePhone(): Boolean {
        val value: String = binding.tilUserPhoneEditText.editText?.text.toString()
        val reg = Regex("^\\d{3}-\\d{3,4}-\\d{4}\$")
        return if (value.isEmpty()) {
            binding.tilUserPhoneEditText.error = "번호를 입력해주세요."
            false
        } else if (value.length != 11) {
            binding.tilUserPhoneEditText.error = "번호 형식이 잘 못 되었습니다."
            false
        } else {
            binding.tilUserPhoneEditText.error = null
            binding.tilUserPhoneEditText.isErrorEnabled = false
            true
        }
    }

}


