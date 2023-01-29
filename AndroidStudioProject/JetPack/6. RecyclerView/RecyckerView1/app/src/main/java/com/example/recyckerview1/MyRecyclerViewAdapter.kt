package com.example.recyckerview1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyckerview1.databinding.ListItemBinding

class MyRecyclerViewAdapter(private val fruitsList : List<Fruit>,
private val clickListener: (Fruit) -> Unit) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(val view : ListItemBinding) : RecyclerView.ViewHolder(view.root){

        fun bind(fruit : Fruit, clickListener: (Fruit) -> Unit){

            view.nameTextView.text = fruit.name
            view.root.setOnClickListener{
                clickListener(fruit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // 레이아웃 팽창기부터 가져옴
        return MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(fruitsList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }
}