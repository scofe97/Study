package com.example.jatlin.presentation.product.create

import android.net.Uri
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.presentation.BasePresenter
import com.example.jatlin.presentation.BaseView

interface ProductCreateContract  {

    // 사용자 액션을 Presenter 에 떠넘기고 UI 업데이트만 하는 클래스
    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showDescription(message: String)

        fun showLog(message: String)

        fun navigateProductFragment()

        fun getProductItem(): ProductEntity?
    }

    // 사용자의 액션을 받아 로직을 처리하고, 모델에 데이터 변경 또는 ui 업데이트 로직
    interface Presenter : BasePresenter {

        fun uploadPhoto(uri: Uri, successHandler: (String) -> Unit, errorHandler: () -> Unit)

        fun insertFirebaseDB(sellerId: String, title: String, price: Int, description: String, imageUrl: String)
    }
}