package com.example.jatlin.presentation.chat.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.jatlin.DBKey
import com.example.jatlin.R
import com.example.jatlin.data.entity.ChatEntity
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.data.entity.UserEntity
import com.example.jatlin.databinding.FragmentChatDetailBinding
import com.example.jatlin.databinding.FragmentProductDetailBinding
import com.example.jatlin.extensions.toast
import com.example.jatlin.presentation.chat.ChatAdapter
import com.example.jatlin.presentation.chat.ChatFragmentDirections
import com.example.jatlin.presentation.product.detail.ProductDetailContract
import com.example.jatlin.presentation.product.detail.ProductDetailFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.android.scope.ScopeFragment


class ChatDetailFragment : ScopeFragment(), ChatDetailContract.View {

    companion object {
        const val TAG = "ChatDetailFragment"
    }

    private lateinit var binding: FragmentChatDetailBinding
    private lateinit var chatDetailAdapter: ChatDetailAdapter
    override val presenter : ChatDetailContract.Presenter by inject()

    private val args : ChatDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    private fun initViews(){
        with(binding){
            sendButton.setOnClickListener {
                val chat = messageEditText.text.toString()
                messageEditText.text = null
                presenter.addChatItem(chat)
            }
        }
    }

    override fun showDescription(message: String) {
        requireActivity().toast(message)
    }

    override fun showLog(message: String) {
        Log.d(ProductDetailFragment.TAG, message)
    }

    override fun setAdapter(chatList: List<ChatEntity>) {
        showLog("${chatList}")
        chatDetailAdapter = ChatDetailAdapter()
        chatDetailAdapter.submitList(chatList)
        binding.chatRecyclerView.adapter = chatDetailAdapter
    }

    override fun chatDB() : DatabaseReference{
        val chatRoomId: Long = args.chatRoomId
        return Firebase.database.reference.child(DBKey.DB_CHATS).child(chatRoomId.toString())
    }


}