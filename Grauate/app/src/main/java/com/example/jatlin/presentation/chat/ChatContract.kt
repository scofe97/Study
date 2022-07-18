package com.example.jatlin.presentation.chat

import com.example.jatlin.data.entity.ChatRoomEntity
import com.example.jatlin.presentation.BasePresenter
import com.example.jatlin.presentation.BaseView

interface ChatContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showDescription(message: String)

        fun showLog(message: String)

        fun navigateDetailChat(chatRoomEntity: ChatRoomEntity)

        fun showChatRoomList()

        fun showEmptyList()

        fun setAdapter(chatRoomList: List<ChatRoomEntity>)

    }

    // 사용자의 액션을 받아 로직을 처리하고, 모델에 데이터 변경 또는 ui 업데이트 로직
    interface Presenter : BasePresenter {


    }
}