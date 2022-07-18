package com.example.jatlin.presentation.chat

import com.example.jatlin.DBKey
import com.example.jatlin.DBKey.Companion.CHILD_CHAT
import com.example.jatlin.data.entity.ChatRoomEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ChatPresenter(
    private val view: ChatContract.View
) : ChatContract.Presenter {

    override val scope: CoroutineScope = MainScope()
    private val chatRoomList = mutableListOf<ChatRoomEntity>()

    private val userDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DBKey.DB_USERS)
    }
    private val chatDB: DatabaseReference by lazy {
        userDB.child(auth.currentUser!!.uid).child(CHILD_CHAT)
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onViewCreated() {
        fetchData()
    }

    private fun fetchData() = scope.launch {
        try {
            view.showLoadingIndicator()
            if (auth.currentUser != null) {
                chatRoomList.clear()
                chatDB.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        view.showLog("${snapshot.getValue(ChatRoomEntity::class.java)}")
                        snapshot.children.forEach {
                            val model = it.getValue(ChatRoomEntity::class.java)
                            view.showLog("데이터 불러오는중 $model")
                            model ?: return

                            chatRoomList.reverse()
                            chatRoomList.add(model)
                            chatRoomList.reverse()

                            view.setAdapter(chatRoomList)
                            listState()
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        view.showLog("채팅목록 불러오기 실패")
                    }
                })
            } else {
                view.showEmptyList()
            }
        } catch (e: Exception) {
            view.showLog("fetchData 오류")
        } finally {
            view.hideLoadingIndicator()
        }
    }

    private fun listState() {
        if (chatRoomList.isEmpty()) {
            view.showEmptyList()
        } else {
            view.showChatRoomList()
        }
    }

    override fun onDestroyView() {

    }

}