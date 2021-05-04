package com.example.mytabstest.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytabstest.AlbumViewModel
import com.example.mytabstest.DetailsActivity
import com.example.mytabstest.DetailsActivityFavorites
import com.example.mytabstest.R
import com.example.mytabstest.adapters.AlbumInfoAdapter
import com.example.mytabstest.pojo.Album

class SecondFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragmentView: View = inflater.inflate(R.layout.fragment_second, container, false)

        recyclerView = fragmentView.findViewById(R.id.recyclerViewListFavourite)

        val albumAdapter = AlbumInfoAdapter()
        albumAdapter.onAlbumClickListener = object : AlbumInfoAdapter.OnAlbumClickListener {
            override fun onAlbumClick(albumInfo: Album) {
                //Toast.makeText(activity, "Клик! ${albumInfo.id}", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, DetailsActivityFavorites::class.java)
                intent.putExtra("userId", albumInfo.userId)
                intent.putExtra("id", albumInfo.id)
                intent.putExtra("title", albumInfo.title)
                startActivity(intent)
            }
        }
        recyclerView.adapter = albumAdapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
        viewModel.albumList.observe(this, Observer {
            albumAdapter.albumInfoList = it
        })

        return fragmentView

    }
}