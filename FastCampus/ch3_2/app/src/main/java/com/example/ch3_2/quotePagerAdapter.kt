package com.example.ch3_2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

// 어댑터
class QuotePagerAdapter(
        // 명언 목록
        private val quotes : List<Quote>,
        private val isNameRevealed : Boolean
) : RecyclerView.Adapter<QuotePagerAdapter.QuoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder =
        // 어떤 뷰를 만드냐 -> QuoteViewHolder 를 생성
            QuoteViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_quote, parent, false)
        )

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        // 어떤 뷰를 bind 하냐 -> 명언목록의 정보 (이름, 명언)

        val actualPosition = position % quotes.size
        holder.bind(quotes[actualPosition], isNameRevealed)
    }

    override fun getItemCount(): Int= Int.MAX_VALUE
    // 실제로 몇개가 만들어지냐

    class QuoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    // 내부 클래스 -> 뷰홀더에 들어갈 정보를 갱신신
        private val quoteTextView : TextView = itemView.findViewById(R.id.quoteTextView)
        private val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)

        @SuppressLint("SetTextI18n")
        fun bind(quote : Quote, isNameRevealed: Boolean){
            quoteTextView.text = "\"${quote.quote}\""

            if(isNameRevealed){
                nameTextView.text = "- ${quote.name}"
                nameTextView.visibility = View.VISIBLE
            }else{
                nameTextView.visibility = View.GONE
            }
        }

    }
}