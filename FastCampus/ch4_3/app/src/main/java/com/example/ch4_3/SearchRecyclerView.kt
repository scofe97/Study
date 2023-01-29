package com.example.ch4_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch4_3.databinding.ViewholderSearchResultItemBinding
import com.example.ch4_3.model.SearchResultEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SearchRecyclerView : RecyclerView.Adapter<SearchRecyclerView.SearchResultItemViewHolder>() {

    private var searchResultList : List<SearchResultEntity> = listOf()
    private lateinit var  searchResultClickListener : (SearchResultEntity) -> Unit

    class SearchResultItemViewHolder(private val binding : ViewholderSearchResultItemBinding, val searchResultClickListener: (SearchResultEntity) -> Unit) : RecyclerView.ViewHolder(binding.root){

        fun bindData(data:SearchResultEntity) = with(binding){
            textTextView.text = data.name
            subtextTextView.text = data.fullAddress
        }
        fun bindViews(data : SearchResultEntity){
            binding.root.setOnClickListener{
                searchResultClickListener(data)
            }
        }

        fun exampleSuspend() {
            val job3 = CoroutineScope(Dispatchers.IO).async {
                (1..10000).sortedByDescending { it }
            }

            CoroutineScope(Dispatchers.Main).launch {
                job3.await()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder {

        // 이미 확장된경우  bind 함수로 바로 결합될 수 있음
        // var view = ViewHolderSearchResultItemBinding.bind(parent)
        var view = ViewholderSearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultItemViewHolder(view, searchResultClickListener)
    }

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) {
        holder.bindData(searchResultList[position])
        holder.bindViews(searchResultList[position])
    }

    override fun getItemCount(): Int = searchResultList.size

    fun setSearchResultList(searchResultList : List<SearchResultEntity>, searchResultClickListener : (SearchResultEntity) -> Unit){
        this.searchResultList = searchResultList
        this.searchResultClickListener = searchResultClickListener
        notifyDataSetChanged()
    }



}