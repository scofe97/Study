package com.example.jatlin.presentation.chat.detail

import com.example.jatlin.DBKey
import com.example.jatlin.data.entity.ChatEntity
import com.example.jatlin.data.entity.ChatRoomEntity
import com.example.jatlin.data.entity.ProductEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ChatDetailPresenter(
    private val view: ChatDetailContract.View
) : ChatDetailContract.Presenter {

    override val scope: CoroutineScope = MainScope()
    private val chatList = mutableListOf<ChatEntity>()

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val listener = object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val chatItem = snapshot.getValue(ChatEntity::class.java)
            chatItem ?: return
            chatList.add(chatItem)
            view.showLog("채팅추가 : $chatItem")
            view.setAdapter(chatList)

        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }

    override fun onViewCreated() {
        chatList.clear()
        view.chatDB().addChildEventListener(listener)
    }

    override fun onDestroyView() {
        view.chatDB().removeEventListener(listener)
    }


    override fun addChatItem(contents: String) {
        scope.launch {
            auth.currentUser?.let {
                val chatItem = ChatEntity(
                    auth.currentUser!!.uid,
                    auth.currentUser?.displayName ?: "익명",
                    auth.currentUser?.photoUrl?.toString(),
                    contents,
                    System.currentTimeMillis()
                )
                view.chatDB().push().setValue(chatItem)

            }

        }
    }
}