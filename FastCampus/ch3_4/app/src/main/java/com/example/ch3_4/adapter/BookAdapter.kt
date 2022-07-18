package com.example.ch3_4.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch3_4.databinding.ItemBookBinding
import com.example.ch3_4.model.Book


class BookAdapter(val clickListener : (Book)->Unit) : ListAdapter<Book, BookAdapter.BookItemViewHolder>(difUtil) {

    inner class BookItemViewHolder(private val binding : ItemBookBinding) : RecyclerView.ViewHolder(binding.root){
        // 뷰 홀더 -> 리사이클러 뷰는 뷰가 미리 몇개 만들어져있고 데이터만 넣음
        // 이 뷰를 뷰홀더라 함

        fun bind(bookModel : Book){
            binding.titleTextView.text = bookModel.title
            binding.descriptionTextView.text = bookModel.description
            binding.root.setOnClickListener{
                clickListener(bookModel)
            }


            // 이미지 로딩 라이브러리
            // 이미지 url 을 불러오는 라이브러리
            // 이미지링크가 http 라서 막히면 매니페스트에서 허용문 추가
            Glide
                // context
                .with(binding.coverImageView.context)
                .load(bookModel.coverSmallUri)
                .into(binding.coverImageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        // currentList 는 자동으로 생성됨
        holder.bind(currentList[position])
    }

    companion object{

        // 리사이클러 뷰가 포지션이 바뀔 때 값을 변경할지 안할지 결정, 같은 값일 때는 바꿀필요가 없음르ㅗ
        val difUtil = object : DiffUtil.ItemCallback<Book>(){
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                // item이 같냐?

               return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                // 컨텐츠가 같냐?
                return oldItem.itemId == newItem.itemId
            }

        }
    }

}