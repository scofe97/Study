package com.example.ch5_shopping_app.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.ch5_shopping_app.R
import com.example.ch5_shopping_app.databinding.ActivityProductDetailBinding
import com.example.ch5_shopping_app.extensions.load
import com.example.ch5_shopping_app.extensions.loadCenterCrop
import com.example.ch5_shopping_app.extensions.toast
import com.example.ch5_shopping_app.presentation.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


internal class ProductDetailActivity : BaseActivity<ProductDetailViewModel, ActivityProductDetailBinding>() {

    companion object {
        const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"

        const val PRODUCT_ORDERED_RESULT_CODE = 99

        fun newIntent(context: Context, productId: Long) =
                // context , 이동할 Activity
                Intent(context, ProductDetailActivity::class.java).apply {
                    putExtra(PRODUCT_ID_KEY, productId)
                }
    }


    override val viewModel by inject<ProductDetailViewModel> {
        // inject 할때 파라미터 전달하는 용도
        parametersOf(
                intent.getLongExtra(PRODUCT_ID_KEY, -1)
        )
    }

    override fun getViewBinding(): ActivityProductDetailBinding = ActivityProductDetailBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.productDetailStateLiveData.observe(this) {
        when (it) {
            is ProductDetailState.UnInitialized -> initViews()
            is ProductDetailState.Loading -> handleLoading()
            is ProductDetailState.Success -> handleSuccess(it)
            is ProductDetailState.Error -> handleError()
            is ProductDetailState.Order -> handleOrder()
        }
    }

    private fun initViews() = with(binding) {
        // 툴바 설정
        // 스타일 -> noActionBar 로 해야 작동함
        setSupportActionBar(toolbar)

        // actionBar -> 툴바

        // 뒤로가기 버튼 (기본값 true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        // 앱바의 아이콘이 정상적으로 나옴
        actionBar?.setDisplayShowHomeEnabled(true)
        title = ""

        toolbar.setNavigationOnClickListener {
            finish()
        }

        orderButton.setOnClickListener {
            viewModel.orderProduct()
        }
    }

    private fun handleLoading() = with(binding) {
        progressBar.isVisible = true
    }

    private fun handleSuccess(state: ProductDetailState.Success) = with(binding) {
        progressBar.isGone = true
        val product = state.productEntity
        title = product.productName
        productCategoryTextView.text = product.productType
        productImageView.loadCenterCrop(product.productImage, 8f)
        productPriceTextView.text = "${product.productPrice}원"
        introductionImageView.load(state.productEntity.productImage)
    }

    private fun handleError() {
        toast("제품 정보를 불러올 수 없습니다.")
        finish()
    }

    private fun handleOrder() {
        // 다시 상품리스트로 돌아가서 결과를 리턴해주기 위한 코드
        setResult(PRODUCT_ORDERED_RESULT_CODE)
        toast("성공적으로 주문이 완료되었습니다.")
        finish()
    }
}



