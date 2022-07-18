package com.example.jatlin.presentation.product.detail

import android.net.Uri
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.presentation.BasePresenter
import com.example.jatlin.presentation.BaseView

interface ProductDetailContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun sellerProfile(userEntity: UserEntity?)

        fun showDescription(message: String)

        fun showLog(message: String)

        fun navigateChatFragment()

        fun navigateProductCreateFragment(productItem : ProductEntity)

        fun getProductItem() : ProductEntity
    }

    // 사용자의 액션을 받아 로직을 처리하고, 모델에 데이터 변경 또는 ui 업데이트 로직
    interface Presenter : BasePresenter {

        fun getFirebaseDB(sellerId: String)

        fun addChatFirebaseDB(product : ProductEntity)

        fun removeProductFirebaseDB()

    }
}