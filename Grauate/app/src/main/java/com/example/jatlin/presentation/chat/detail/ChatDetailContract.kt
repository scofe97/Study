package com.example.jatlin.presentation.chat.detail

import com.example.jatlin.data.entity.ChatEntity
import com.example.jatlin.data.entity.ChatRoomEntity
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.presentation.BasePresenter
import com.example.jatlin.presentation.BaseView
import com.google.firebase.database.DatabaseReference

interface ChatDetailContract {
    interface View : BaseView<Presenter> {

        fun showDescription(message: String)

        fun showLog(message: String)

        fun setAdapter(chatList: List<ChatEntity>)

        fun chatDB() : DatabaseReference

    }

    // 사용자의 액션을 받아 로직을 처리하고, 모델에 데이터 변경 또는 ui 업데이트 로직
    interface Presenter : BasePresenter {

        fun addChatItem( content : String)

    }
}