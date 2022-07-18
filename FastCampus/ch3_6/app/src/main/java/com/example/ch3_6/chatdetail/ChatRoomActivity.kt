package com.example.ch3_6.chatdetail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch3_6.ChatItemAdapter
import com.example.ch3_6.DBkey.Companion.DB_CHATS
import com.example.ch3_6.R
import com.example.ch3_6.databinding.ActivityChatRoomBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ChatRoomActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatRoomBinding
    private val auth : FirebaseAuth by lazy{
        Firebase.auth
    }
    private val adapter = ChatItemAdapter()
    private val chatList = mutableListOf<ChatItem>()
    private var chatDB : DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatKey = intent.getLongExtra("chatKey", -1)

        binding.chatRecyclerView.adapter = adapter
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(this)

        chatDB = Firebase.database.reference.child(DB_CHATS).child("$chatKey")

        chatDB!!.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatItem = snapshot.getValue(ChatItem::class.java)
                chatItem ?: return

                Log.d("test chat", "$chatItem")

                chatList.add(chatItem)
                adapter.submitList(chatList)
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        binding.sendButton.setOnClickListener{
            val chatItem = auth.currentUser?.uid?.let { it1 ->
                ChatItem(
                    sendId = it1,
                    message = binding.messageEditText.text.toString()
                )
            }
            chatDB!!.push().setValue(chatItem)
        }
    }
}