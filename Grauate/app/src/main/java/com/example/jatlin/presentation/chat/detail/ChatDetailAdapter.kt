package com.example.jatlin.presentation.chat.detail

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jatlin.R
import com.example.jatlin.data.entity.ChatEntity
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.databinding.ItemChatMeBinding
import com.example.jatlin.databinding.ItemChatYouBinding
import com.example.jatlin.databinding.ItemProductBinding
import com.example.jatlin.extensions.loadCenterCrop
import com.example.jatlin.presentation.product.ProductAdapter
import com.google.firebase.auth.FirebaseAuth
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ChatDetailAdapter() : ListAdapter<ChatEntity, RecyclerView.ViewHolder>(diffUtil) {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    inner class ViewHolderMe(private val binding: ItemChatMeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatEntity: ChatEntity){
            Log.d("adapter", "자기자신 뷰홀더 바인드")
            val date = Date(chatEntity.createdAt)
            val dataFormat = SimpleDateFormat("h:mm a", Locale("ko", "KR"))
            val strDate = dataFormat.format(date)

            with(binding){
                chatItemContents.text = chatEntity.chatContents
                chatItemTime.text = strDate
            }
        }

    }

    inner class ViewHolderYou(private val binding : ItemChatYouBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatEntity: ChatEntity){
            Log.d("adapter", "다른사람 뷰홀더 바인드")
            val date = Date(chatEntity.createdAt)
            val dataFormat = SimpleDateFormat("h:mm a", Locale("ko", "KR"))
            val strDate = dataFormat.format(date)

            with(binding){
                chatProfileImage.clipToOutline = true
                chatEntity.userImage?.let{
                    chatProfileImage.loadCenterCrop(chatEntity.userImage, 60f)
                }?: kotlin.run{
                    chatProfileImage.setImageResource(R.drawable.basic_profile)
                }
                chatItemName.text = chatEntity.userName
                chatItemContents.text = chatEntity.chatContents
                chatItemTime.text = strDate
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(currentList[position].userId == auth.currentUser?.uid){
            1
        }else{
            2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return when(viewType){
            1 ->{
                Log.d("adapter", "자기자신 뷰홀더 생성")
                ViewHolderMe(ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else->{
                Log.d("adapter", "다른사람 뷰홀더 생성")
                ViewHolderYou(ItemChatYouBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // holder.bind(currentList[position])
        Log.d("adapter", "확인 ${currentList[position].userId}")
        Log.d("adapter", "확인 ${auth.currentUser?.uid}")
        if(currentList[position].userId == auth.currentUser?.uid){
            (holder as ViewHolderMe).bind(currentList[position])
        }else{
            (holder as ViewHolderYou).bind(currentList[position])
        }


    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatEntity>() {
            override fun areContentsTheSame(oldItem: ChatEntity, newItem: ChatEntity): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ChatEntity, newItem: ChatEntity): Boolean {
                return oldItem.createdAt == newItem.createdAt
            }

        }
    }

}