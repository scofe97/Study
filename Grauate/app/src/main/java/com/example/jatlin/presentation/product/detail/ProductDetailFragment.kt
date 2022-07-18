package com.example.jatlin.presentation.product.detail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.jatlin.R
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.databinding.FragmentProductDetailBinding
import com.example.jatlin.extensions.loadCenterCrop
import com.example.jatlin.extensions.toGone
import com.example.jatlin.extensions.toVisible
import com.example.jatlin.extensions.toast
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.scope.ScopeFragment
import java.text.DecimalFormat


class ProductDetailFragment : ScopeFragment(), ProductDetailContract.View {

    companion object {
        const val TAG = "ProductDetailFragment"
    }

    private lateinit var binding: FragmentProductDetailBinding

    override val presenter: ProductDetailContract.Presenter by inject()

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVies()
        presenter.onViewCreated()
    }

    private fun initVies() {
        val dec = DecimalFormat("#,###원")
        val productItem = getProductItem()
        presenter.getFirebaseDB(productItem.sellerId)

        with(binding) {

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            itemTitleTextView.text = productItem.productName
            itemPriceTextView.text = dec.format(productItem.productPrice)
            itemIntroductionTextView.text = productItem.productIntroduction
            productImageView.loadCenterCrop(productItem.productImage, 0f)

            chatButton.setOnClickListener {
                presenter.addChatFirebaseDB(productItem)
            }
            if (productItem.sellerId == auth.currentUser?.uid) {
                toolbar.inflateMenu(R.menu.menu_update_delete_toolbar)

                toolbar.setOnMenuItemClickListener{
                    when(it.itemId){
                        R.id.productUpdateButton ->{
                            navigateProductCreateFragment(productItem)
                            true
                        }
                        R.id.productDeleteButton ->{
                            showDeleteConfirmDialog()
                            true
                        }
                        else -> false
                    }
                }
            }


        }
    }

    override fun showLoadingIndicator() {
        binding.progressBar.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.toGone()
    }

    override fun sellerProfile(userEntity: UserEntity?) {
        with(binding) {
            sellerNameTextView.text = userEntity?.userName ?: "익명"


            var number = userEntity?.userPhoneNumber
            if (number != null) {
                val first = number?.slice(IntRange(0, 2))
                val second = number?.slice(IntRange(3, 6))
                val last = number?.slice(IntRange(7, 10))
                val phoneText = "$first-$second-$last"
                sellerPhoneNuberTextView.text = phoneText
            } else {
                sellerPhoneNuberTextView.text = "번호 없음"
            }

            sellerImageView.clipToOutline = true
            userEntity?.userImage?.let {
                sellerImageView.loadCenterCrop(userEntity.userImage, 0f)
            } ?: kotlin.run {
                sellerImageView.setImageResource(R.drawable.basic_profile)
            }
        }
    }

    override fun showDescription(message: String) {
        requireActivity().toast(message)
    }

    override fun showLog(message: String) {
        Log.d(TAG, message)
    }

    override fun navigateChatFragment() {
        findNavController().navigate(R.id.action_productDetailFragment_to_chatFragment)
    }

    override fun navigateProductCreateFragment(productItem: ProductEntity) {
        showLog("업데이트 이동함수")
        val bundle = bundleOf("productItem" to productItem)
        findNavController().navigate(R.id.action_productDetailFragment_to_productCreateFragment, bundle)
    }

    override fun getProductItem(): ProductEntity {
        return arguments?.get("productItem") as ProductEntity
    }

    private fun showDeleteConfirmDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("정말로 상품을 삭제하시겠어요?")
            .setNegativeButton("안할래요") { _, _ -> }
            .setPositiveButton("삭제할래요") { _, _ ->
                presenter.removeProductFirebaseDB()
                findNavController().popBackStack()
            }
            .show()
    }

}