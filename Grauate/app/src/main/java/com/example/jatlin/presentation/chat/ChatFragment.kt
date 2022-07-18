package com.example.jatlin.presentation.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.jatlin.R
import com.example.jatlin.data.entity.ChatRoomEntity
import com.example.jatlin.databinding.FragmentChatBinding
import com.example.jatlin.extensions.toGone
import com.example.jatlin.extensions.toVisible
import com.example.jatlin.extensions.toast
import com.example.jatlin.presentation.product.detail.ProductDetailFragment
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.scope.ScopeFragment


class ChatFragment : ScopeFragment(), ChatContract.View {

    companion object{
        const val TAG = "ChatFragment"
    }

    private lateinit var binding : FragmentChatBinding
    private lateinit var chatAdapter: ChatAdapter
    override val presenter : ChatContract.Presenter by inject()

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.onViewCreated()
    }

    private fun initViews(){

    }

    override fun setAdapter(chatRoomList: List<ChatRoomEntity>) {
        chatAdapter = ChatAdapter(onItemClicked = { chatItem ->
            if (auth.currentUser != null) {
                // 로그인을 한 상태
                navigateDetailChat(chatItem)
            } else {
                // 로그인을 안한 상태
                showDescription(R.string.require_login.toString())
            }
        })
        chatAdapter.submitList(chatRoomList)
        binding.chatRoomRecyclerView.adapter = chatAdapter
    }

    override fun showChatRoomList() {
        with(binding){
            emptyResultTextView.toGone()
            chatRoomRecyclerView.toVisible()
        }
    }

    override fun showEmptyList() {
        with(binding){
            emptyResultTextView.toVisible()
            chatRoomRecyclerView.toGone()
        }
    }

    override fun showLoadingIndicator() {
        binding.progressBar.toVisible()
    }

    override fun hideLoadingIndicator() {
        binding.progressBar.toGone()
    }

    override fun showDescription(message: String) {
        requireActivity().toast(message)
    }

    override fun showLog(message: String) {
        Log.d(ProductDetailFragment.TAG, message)
    }

    override fun navigateDetailChat(chatRoomEntity: ChatRoomEntity) {
        val action = ChatFragmentDirections.actionChatFragmentToChatDetailFragment(chatRoomEntity.createdAt)
        findNavController().navigate(action)
    }


}