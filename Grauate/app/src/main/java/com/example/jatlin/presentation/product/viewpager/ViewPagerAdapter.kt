package com.example.jatlin.presentation.product.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jatlin.R
import com.example.jatlin.databinding.ItemViewpagerBinding

class ViewPagerAdapter (colorList: ArrayList<Int>, titleList : ArrayList<String>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var itemColor = colorList
    var itemTitle = titleList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemColor.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(itemColor[position], itemTitle[position])
    }

    inner class PagerViewHolder(private val binding : ItemViewpagerBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(color : Int, title: String){
            with(binding){
                viewpagerBack.setImageResource(color)
                viewpagerTitle.text = title
            }
        }
    }
}