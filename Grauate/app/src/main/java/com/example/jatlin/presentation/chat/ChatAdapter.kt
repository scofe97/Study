package com.example.jatlin.presentation.chat

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jatlin.DBKey
import com.example.jatlin.R
import com.example.jatlin.data.entity.ChatRoomEntity
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.databinding.ItemChatRoomBinding
import com.example.jatlin.extensions.loadCenterCrop
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ChatAdapter(val onItemClicked: (ChatRoomEntity) -> Unit) :
    ListAdapter<ChatRoomEntity, ChatAdapter.ViewHolder>(diffUtil) {

    private val userDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DBKey.DB_USERS)
    }
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    inner class ViewHolder(private val binding: ItemChatRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(chatRoomEntity: ChatRoomEntity) {
            with(binding){
                chatItemProfileImage.clipToOutline = true

                if(auth.currentUser?.uid == chatRoomEntity.buyerId){
                    userDB.child(chatRoomEntity.sellerId).get().addOnSuccessListener {
                        val sellerUser = it.getValue(UserEntity::class.java)
                        chatItemProfileName.text = sellerUser?.userName ?: "익명"
                        sellerUser?.userImage?.let{
                            chatItemProfileImage.loadCenterCrop(sellerUser.userImage, 60f)
                        }?: kotlin.run{
                            chatItemProfileImage.setImageResource(R.drawable.basic_profile)
                        }
                    }.addOnFailureListener{
                        Log.e("firebase", "Error getting data", it)
                    }

                }else{
                    userDB.child(chatRoomEntity.buyerId).get().addOnSuccessListener {
                        val sellerUser = it.getValue(UserEntity::class.java)
                        chatItemProfileName.text = sellerUser?.userName ?: "익명"
                        sellerUser?.userImage?.let{
                            chatItemProfileImage.loadCenterCrop(sellerUser.userImage, 60f)
                        }?: kotlin.run{
                            chatItemProfileImage.setImageResource(R.drawable.basic_profile)
                        }
                    }.addOnFailureListener{
                        Log.e("firebase", "Error getting data", it)
                    }
                }

                chatItemTitle.text = chatRoomEntity.chatTitle

                root.setOnClickListener {
                    onItemClicked(chatRoomEntity)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemChatRoomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatRoomEntity>() {
            override fun areContentsTheSame(oldItem: ChatRoomEntity, newItem: ChatRoomEntity): Boolean {
                // 현재 아이템과 새로운 아이템이 같은가
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ChatRoomEntity, newItem: ChatRoomEntity): Boolean {
                // 현재 노출되는 item 과 새로운 값이 동일한가?
                return oldItem.createdAt == newItem.createdAt
//             }

            }
        }
    }
}