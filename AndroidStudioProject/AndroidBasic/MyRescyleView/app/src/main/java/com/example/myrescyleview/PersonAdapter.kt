package com.example.myrescyleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.person_item.view.*

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    var items = ArrayList<Person>()

    lateinit var listener: OnPersonItemClickListener

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 몇개를 보여줌?
        // parent 뷰홀더가 담고있을 아이템 최상위 레이아웃
        // false는 바로 보여주지는 않음
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 스크롤을 하며 재사용하며 보임 그 객체의 데이터를 이용해 값 할당
        // 몇번째 보여줘야하는지 알려줌
        val item = items[position]
        holder.setItem(item)
    }

    // 뷰홀더는 객체의 재사용
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.setOnClickListener{
                // 리스너 객체로 다시 던져줌
                listener?.onItemClick(this, itemView, adapterPosition)
            }
        }

        fun setItem(item:Person){
            itemView.output1.text = item.name
            itemView.output2.text = item.mobile
        }
    }
}