package com.example.ch3_6.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch3_6.databinding.ItemArticleBinding
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(val onItemClicked: (ArticleModel)->Unit) : ListAdapter<ArticleModel, ArticleAdapter.ViewHolder>(diffUtil){

    inner class ViewHolder(private val binding : ItemArticleBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(articleModel : ArticleModel){

            val format = SimpleDateFormat("MM월 dd일")
            val date = Date(articleModel.createAt)

            binding.titleTextView.text = articleModel.title
            binding.dateTextView.text = format.format(date).toString()
            binding.priceTextView.text = articleModel.price

            // 글라이드 사용용
            if(articleModel.imageUrl.isNotEmpty()){
                Glide.with(binding.thumbnailImageView)
                    .load(articleModel.imageUrl)
                    .into(binding.thumbnailImageView)
            }

            binding.root.setOnClickListener{
                // 화면을 클릭했을 경우
                onItemClicked(articleModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //
        return ViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ArticleModel>(){
            override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                // 현재 아이템과 새로운 아이템이 같은가
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
                // 현재 노출되는 item 과 새로운 값이 동일한가?
                return oldItem.createAt == newItem.createAt
            }

        }
    }
}