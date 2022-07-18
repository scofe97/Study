package com.example.jatlin.presentation.product.create

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.text.set
import androidx.navigation.fragment.findNavController
import com.example.jatlin.R
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.databinding.FragmentProductCreateBinding
import com.example.jatlin.databinding.FragmentProfileBinding
import com.example.jatlin.extensions.loadCenterCrop
import com.example.jatlin.extensions.toGone
import com.example.jatlin.extensions.toVisible
import com.example.jatlin.extensions.toast
import com.example.jatlin.presentation.login.LoginFragment
import com.example.jatlin.presentation.product.ProductContract
import com.example.jatlin.presentation.profile.ProfileContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.scope.ScopeFragment
import java.net.URI


class ProductCreateFragment : ScopeFragment(), ProductCreateContract.View {

    companion object {
        const val TAG = "ProductCreateFragment"
    }

    private var selectedUri: Uri? = null
    private lateinit var binding: FragmentProductCreateBinding
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override val presenter: ProductCreateContract.Presenter by inject()

    //// 권한요청, intent 함수

    // 권한요청
    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                startContentProvider()
            } else {
                showDescription("권한요청 거부됨")
            }

        }

    // 저장소 접근
    private val requestStorageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data
                if (uri != null) {
                    binding.productPhotoImageView.setImageURI(uri)
                    selectedUri = uri
                } else {
                    showDescription("사진을 가져오지 못했습니다.")
                }
            } else {
                showDescription("에러발생")
            }
        }

    override fun showLoadingIndicator() {
        Log.d(TAG, "showLoadingIndicator")
        binding.progressBar.toVisible()
    }

    override fun hideLoadingIndicator() {
        Log.d(TAG, "hideLoadingIndicator")
        binding.progressBar.toGone()
    }

    override fun showDescription(message: String) {
        requireActivity().toast(message)
    }

    override fun showLog(message: String) {
        Log.d(TAG, message)
    }

    override fun navigateProductFragment() {
        findNavController().navigate(R.id.action_productCreateFragment_to_productFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductCreateBinding.inflate(layoutInflater)
        return binding.root
    }


    // 완전히 view 가 생성됨
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    private fun initViews() {
        with(binding) {
            val productItem = getProductItem()
            if(productItem != null){
                productNameEditText.setText(productItem.productName)
                productPricerEditText.setText(productItem.productPrice.toString())
                productIntroductionEditText.setText(productItem.productIntroduction)
            }

            productImageAddButton.setOnClickListener {
                when {
                    // 권한허가됨
                    ContextCompat.checkSelfPermission(
                        requireActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED -> {
                        startContentProvider()
                    }
                    // 권한을 요청할지 물어봄
                    shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                        showPermissionContextPopup()
                    }
                    else -> {
                        // 권한요청함
                        requestPermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
            }

            submitButton.setOnClickListener {
                showLog("${selectedUri}")
                showLoadingIndicator()
                if (productNameEditText.text.isNotEmpty() && productNameEditText.text.isNotEmpty() && selectedUri != null) {
                    val title = productNameEditText.text.toString()
                    val price = (productPricerEditText.text.toString()).toInt()
                    val sellerId = auth.currentUser?.uid.orEmpty()
                    val introduction = productIntroductionEditText.text.toString()

                    val photoUri = selectedUri ?: return@setOnClickListener
                    presenter.uploadPhoto(photoUri,
                        successHandler = { uri ->
                            presenter.insertFirebaseDB(
                                sellerId,
                                title,
                                price,
                                introduction,
                                uri
                            )
                            navigateProductFragment()
                        },
                        errorHandler = {
                            showDescription("사진업로드 실패")
                            navigateProductFragment()
                        }
                    )
                } else {
                    showDescription("제목, 가격, 이미지 모두 입력해주세요.")
                }
                hideLoadingIndicator()
            }
        }
    }

    // 인텐트 타입
    private fun startContentProvider() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        requestStorageLauncher.launch(intent)
    }

    // 권한관련 질문 대화상자
    private fun showPermissionContextPopup() {
        AlertDialog.Builder(requireActivity())
            .setTitle("권한이 필요합니다.")
            .setMessage("사진을 가져오기 위해 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                requestPermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            .create()
            .show()
    }

    override fun getProductItem(): ProductEntity? {
        return arguments?.get("productItem") as ProductEntity?
    }

}