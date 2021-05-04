package com.example.mytabstest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytabstest.pojo.Album
import com.example.mytabstest.R

class AlbumInfoAdapter() : RecyclerView.Adapter<AlbumInfoAdapter.AlbumInfoViewHolder>() {

    var albumInfoList: List<Album> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onAlbumClickListener: OnAlbumClickListener? = null

    inner class AlbumInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //находим поля
        val textViewAlbumName: TextView = itemView.findViewById(R.id.textViewAlbumName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album_info, parent, false)
        return AlbumInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumInfoViewHolder, position: Int) {
        val albumInfo = albumInfoList[position]
        holder.textViewAlbumName.text = "${albumInfo.id}. ${albumInfo.title}"

        //назначим слушатель
        holder.itemView.setOnClickListener {
            onAlbumClickListener?.onAlbumClick(albumInfo)
        }
    }

    override fun getItemCount(): Int {
        return albumInfoList.size
    }

    interface OnAlbumClickListener{
        fun onAlbumClick(albumInfo: Album)
    }

}