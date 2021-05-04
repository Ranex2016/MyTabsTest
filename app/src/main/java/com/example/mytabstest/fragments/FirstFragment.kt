package com.example.mytabstest.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytabstest.DetailsActivity
import com.example.mytabstest.DetailsActivityFavorites
import com.example.mytabstest.R
import com.example.mytabstest.adapters.AlbumInfoAdapter
import com.example.mytabstest.api.ApiFactory
import com.example.mytabstest.pojo.Album
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FirstFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView: View = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = fragmentView.findViewById(R.id.recyclerViewList)

        val albumAdapter = AlbumInfoAdapter()
        albumAdapter.onAlbumClickListener = object : AlbumInfoAdapter.OnAlbumClickListener {
            override fun onAlbumClick(albumInfo: Album) {
                //Toast.makeText(activity, "Клик! ${albumInfo.id}", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity,DetailsActivity::class.java)
                intent.putExtra("userId", albumInfo.userId)
                intent.putExtra("id", albumInfo.id)
                intent.putExtra("title", albumInfo.title)
                startActivity(intent)
            }
        }
        recyclerView.adapter = albumAdapter
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        //Получим данные
        val disposable = ApiFactory.apiService.getAlbumAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TEST_OF_LOADING_DATA", it.toString())
                albumAdapter.albumInfoList = it

            }, {
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)

        return fragmentView
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}