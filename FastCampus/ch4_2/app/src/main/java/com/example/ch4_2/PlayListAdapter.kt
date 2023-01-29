package com.example.ch4_2

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch4_2.databinding.ItemMusicBinding
import javax.security.auth.callback.Callback

class PlayListAdapter(private val callback : (MusicModel)-> Unit) : ListAdapter<MusicModel, PlayListAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val bind : ItemMusicBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(item : MusicModel){
            bind.itemTrackTextView.text = item.track
            bind.itemArtistTextView.text = item.artist

            Glide.with(bind.itemCoverImageView.context)
                .load(item.coverUrl)
                .into(bind.itemCoverImageView)

            // itemView -> 뷰홀더임
            if(item.isPlaying){
                itemView.setBackgroundColor(Color.GRAY)
            }else{
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }
            itemView.setOnClickListener{
                callback(item)
            }

        }
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<MusicModel>(){
            override fun areItemsTheSame(oldItem: MusicModel, newItem: MusicModel): Boolean {
                if((oldItem == newItem).not()) {

                    /*Log.d("test1","${oldItem.id}  ${newItem.id}  ${oldItem == newItem}")
                    Log.d("test1","$oldItem")
                    Log.d("test1","$newItem")*/
                }
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MusicModel, newItem: MusicModel): Boolean {
                if( (oldItem.id == newItem.id).not() ) {
                    Log.d("test","$oldItem")
                    Log.d("test","$newItem")
                }
                return oldItem == newItem

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMusicBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}