package com.example.ch5_shopping_app.presentation.list

import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import com.example.ch5_shopping_app.databinding.FragmentProductListBinding
import com.example.ch5_shopping_app.databinding.FragmentProfileBinding
import com.example.ch5_shopping_app.extensions.toast
import com.example.ch5_shopping_app.presentation.BaseFragment
import com.example.ch5_shopping_app.presentation.adapter.ProductListAdapter
import com.example.ch5_shopping_app.presentation.detail.ProductDetailActivity
import com.example.ch5_shopping_app.presentation.main.MainActivity
import com.example.ch5_shopping_app.presentation.profile.ProfileViewModel
import org.koin.android.ext.android.inject

internal class ProductListFragment : BaseFragment<ProductListViewModel, FragmentProductListBinding>() {

    companion object{
        const val TAG = "ProductListFragment"
    }

    override val viewModel by inject<ProductListViewModel>()

    override fun getViewBinding(): FragmentProductListBinding = FragmentProductListBinding.inflate(layoutInflater)

    private val adapter = ProductListAdapter()

    private val startProductDetailForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                // 성공적으로 처리 완료 이후 동작

                if(result.resultCode == ProductDetailActivity.PRODUCT_ORDERED_RESULT_CODE){
                    (requireActivity() as MainActivity).viewModel.refreshOrderList()
                }
            }

    override fun observeData() = viewModel.productListStateLiveData.observe(this){
        when(it){
            is ProductListState.UnInitialized ->{
                initView(binding)
            }
            is ProductListState.Loading ->{
                handleLoadingState()
            }
            is ProductListState.Success ->{
                handleSuccessState(it)
            }
            is ProductListState.Error ->{
                handleErrorState()
            }
        }
    }

    private fun initView(binding : FragmentProductListBinding) = with(binding){
        Log.d(TAG, "state : initViews")
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    private fun handleLoadingState() = with(binding){
        Log.d(TAG, "state : handleLoadingState")
        refreshLayout.isRefreshing = true
    }

    private fun handleSuccessState(state: ProductListState.Success) = with(binding) {
        Log.d(TAG, "state : handleSuccessState")
        // 새로고침 완료시,
        // 새로고침 아이콘이 사라질 수 있게 isRefreshing = false
        refreshLayout.isRefreshing = false

        if (state.productList.isEmpty()) {
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        } else {
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setProductList(state.productList) {

                // 어댑터에서 클릭이벤트 리스너 함수 적용
                startProductDetailForResult.launch(
                        // 현재 Context 를 가져옴 ( requireContext() )
                        // 현재 Activity -> ProductDetailActivity 로 이동
                        // ID 값
                        ProductDetailActivity.newIntent(requireContext(), it.id)
                )
                requireContext().toast("Product Entity : $it")
            }
        }
    }

    private fun handleErrorState() {
        Toast.makeText(requireContext(), "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }
}