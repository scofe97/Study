package com.example.ch3_6.chatlist


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch3_6.databinding.ItemArticleBinding
import com.example.ch3_6.databinding.ItemChatListBinding
import java.text.SimpleDateFormat
import java.util.*

class ChatListAdapter(val onItemClicked: (ChatListItem)->Unit) : ListAdapter<ChatListItem, ChatListAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding : ItemChatListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(chatListItem : ChatListItem){

            binding.root.setOnClickListener{
                // 화면을 클릭했을 경우
                onItemClicked(chatListItem)
            }
            binding.chatRoomTitleTextView.text = chatListItem.itemTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //
        return ViewHolder(ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ChatListItem>(){
            override fun areContentsTheSame(oldItem: ChatListItem, newItem: ChatListItem): Boolean {
                // 현재 아이템과 새로운 아이템이 같은가
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ChatListItem, newItem: ChatListItem): Boolean {
                // 현재 노출되는 item 과 새로운 값이 동일한가?
                return oldItem.key == newItem.key
            }

        }
    }
}