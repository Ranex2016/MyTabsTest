package com.example.mytabstest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytabstest.adapters.AlbumDetailsAdapter
import com.example.mytabstest.api.ApiFactory
import com.example.mytabstest.pojo.Album
import com.example.mytabstest.pojo.AlbumDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsActivityFavorites : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterDetails: AlbumDetailsAdapter
    //private var albumDetailsList = listOf<AlbumDetails>()

    private var album = Album()

    private var compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: AlbumViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_favorites)

        recyclerView = findViewById(R.id.recyclerViewDetailsFavorites)
        adapterDetails = AlbumDetailsAdapter()
        recyclerView.adapter = adapterDetails
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        val intent = intent
        val userId = intent.getIntExtra("userId", 0)
        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")

        album = Album(userId, id, title)

        //Toast.makeText(this, "$title \n $id \n $userId", Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        // Получим данные
        viewModel.getAlbumDetailsList(id).observe(this, Observer {
            adapterDetails.albumDetailsList = it
            Intent(this,ImageActivity::class.java)
        })
        adapterDetails.onImageClickListener = object : AlbumDetailsAdapter.OnImageClickListener{
            override fun onImageClick(url: String) {
                val intent = Intent(this@DetailsActivityFavorites,ImageActivity::class.java)
                intent.putExtra("url",url)
                startActivity(intent)
            }
        }
    }

    fun onClickDellAlbum(view: View) {
        System.out.println("клик!!!")
        val disposable2 = ApiFactory.apiService.getAlbumAll()
            .subscribeOn(Schedulers.io())
            .subscribe({
                album.id?.let { it1 -> viewModel.deleteAlbumDetails(it1) }
                album.id?.let { it1 -> viewModel.deleteAlbum(it1) }
                finish()
            }, {
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
                album.id?.let { it1 -> viewModel.deleteAlbumDetails(it1) }
                album.id?.let { it1 -> viewModel.deleteAlbum(it1) }
                finish()
            })
        compositeDisposable.add(disposable2)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}