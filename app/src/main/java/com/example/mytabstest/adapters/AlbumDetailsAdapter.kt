package com.example.mytabstest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytabstest.R
import com.example.mytabstest.pojo.AlbumDetails
import com.squareup.picasso.Picasso

class AlbumDetailsAdapter : RecyclerView.Adapter<AlbumDetailsAdapter.AlbumViewHolder>() {

    var albumDetailsList:List<AlbumDetails> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onImageClickListener:OnImageClickListener? = null

    inner class AlbumViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val imageView: ImageView = itemView.findViewById(R.id.small_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumDetailsList[position]
        Picasso.get().load(album.thumbnailUrl).into(holder.imageView)

        holder.imageView.setOnClickListener {
            album.thumbnailUrl?.let { it1 -> onImageClickListener?.onImageClick(it1) }
        }
    }

    override fun getItemCount(): Int {
        return albumDetailsList.size
    }

    interface OnImageClickListener{
        fun onImageClick(url: String)
    }
}