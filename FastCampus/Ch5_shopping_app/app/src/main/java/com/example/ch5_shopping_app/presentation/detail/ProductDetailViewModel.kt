package com.example.ch5_shopping_app.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ch5_shopping_app.data.entity.product.ProductEntity
import com.example.ch5_shopping_app.domain.GetProductItemUseCase
import com.example.ch5_shopping_app.domain.OrderProductItemUseCase
import com.example.ch5_shopping_app.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProductDetailViewModel(
    private val productId : Long,
    private val getProductItemUseCase: GetProductItemUseCase,
    private val orderProductItemUseCase: OrderProductItemUseCase
) : BaseViewModel() {

    private val _productDetailStateLiveData = MutableLiveData<ProductDetailState>(ProductDetailState.UnInitialized)
    val productDetailStateLiveData = _productDetailStateLiveData

    private lateinit var productEntity : ProductEntity

    override fun fetchData(): Job = viewModelScope.launch {
        setState(ProductDetailState.Loading)

        // ID 값으로 정보 가져옴
        getProductItemUseCase(productId)?.let{ product ->
            productEntity = product
            setState(
                    ProductDetailState.Success(product)
            )
        } ?: kotlin.run{
            setState(ProductDetailState.Error)
        }
    }

    fun orderProduct() = viewModelScope.launch {

        // 만약 개체값이 아직 초기화가 안됨
        if(::productEntity.isInitialized){

            // ID 값을 유스케이스로 가져옴
            val productId = orderProductItemUseCase(productEntity)

            // 만약 개체 ID와  지금 개체값이 동일하면 주문상태 적용
            if(productEntity.id == productId){
                setState(ProductDetailState.Order)
            }
        }else{
            setState(ProductDetailState.Order)
        }
    }

    private fun setState(state : ProductDetailState){
        _productDetailStateLiveData.postValue(state)
    }

}