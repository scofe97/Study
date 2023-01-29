package com.example.ch3_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ch3_6.chatdetail.ChatItem
import com.example.ch3_6.databinding.ActivityChatRoomBinding
import com.example.ch3_6.databinding.ItemChatBinding
import com.example.ch3_6.databinding.ItemChatListBinding

class ChatItemAdapter : ListAdapter<ChatItem, ChatItemAdapter.ViewHolder>(diffUtil){
    inner class ViewHolder(private val binding : ItemChatBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(chatItem: ChatItem){
            binding.senderTextView.text = chatItem.sendId
            binding.messageTextView.text = chatItem.message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ChatItem>(){
            override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                // 현재 아이템과 새로운 아이템이 같은가
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem): Boolean {
                // 현재 노출되는 item 과 새로운 값이 동일한가?
                return oldItem == newItem
            }

        }
    }


}