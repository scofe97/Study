package com.example.ch3_4.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ch3_4.MainActivity
import com.example.ch3_4.databinding.ActivityMainBinding
import com.example.ch3_4.databinding.ItemHistoryBinding
import com.example.ch3_4.model.History

// 문자열이 인자이고 반환값이 void인 함수가 인자로 들어감
class HistoryAdapter(val historyDeleteClickedListener:(String)->Unit, val search:(String)->Unit) : ListAdapter<History, HistoryAdapter.HistoryItemViewHolder>(difUtil) {

    private lateinit var bind : ActivityMainBinding

    inner class HistoryItemViewHolder(private val binding : ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(history : History){
            binding.historyKeywordTextView.text = history.keyword

            binding.historyKeywordDeleteButton.setOnClickListener{
                historyDeleteClickedListener(history.keyword.orEmpty())
            }
            binding.root.setOnClickListener{
                search(history.keyword.orEmpty())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HistoryAdapter.HistoryItemViewHolder {
        return HistoryItemViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryItemViewHolder, position: Int) {
        // currentList 는 자동으로 생성됨
        holder.bind(currentList[position])
    }

    companion object{
        // 리사이클러 뷰가 포지션이 바뀔 때 값을 변경할지 안할지 결정, 같은 값일 때는 바꿀필요가 없음르ㅗ
        val difUtil = object : DiffUtil.ItemCallback<History>(){
            override fun areItemsTheSame(oldItem: History, newItem: History) = oldItem == newItem


            override fun areContentsTheSame(oldItem: History, newItem: History) = oldItem.keyword == newItem.keyword


        }
    }

}