package com.example.jatlin.presentation.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jatlin.data.entity.ProductEntity
import com.example.jatlin.databinding.ItemProductBinding
import com.example.jatlin.extensions.loadCenterCrop
import com.google.firebase.auth.FirebaseAuth
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class ProductAdapter(val onItemClicked: (ProductEntity) -> Unit) : ListAdapter<ProductEntity, ProductAdapter.ViewHolder>(diffUtil) {

    // RecyclerView 에서 사용될 ViewHolder 설정
    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(productModel: ProductEntity) {
            val dec = DecimalFormat("#,###원")

            with(binding){
                productNameTextView.text = productModel.productName
                productPriceTextView.text = dec.format(productModel.productPrice)
                productImageView.loadCenterCrop(productModel.productImage, 4f)

                root.setOnClickListener{
                    onItemClicked(productModel)
                }
            }
        }
    }

    // ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    // ViewHolder 데이터 뿌리기
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }


    // diffUtil 로 데이터 중복여부 검사
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.createdAt == newItem.createdAt
            }

        }
    }

}