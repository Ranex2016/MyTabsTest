package com.example.mytabstest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
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

class DetailsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterDetails: AlbumDetailsAdapter
    private var albumDetailsList = listOf<AlbumDetails>()

    private var album = Album()

    private var compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        recyclerView = findViewById(R.id.recyclerViewDetails)
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

        //Получим данные
        val disposable = ApiFactory.apiService.getAlbumDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Log.d("TEST_OF_LOADING_DATA", it.toString())

                adapterDetails.albumDetailsList = it
                albumDetailsList = it

            }, {
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)

        adapterDetails.onImageClickListener = object : AlbumDetailsAdapter.OnImageClickListener{
            override fun onImageClick(url: String) {
                val intent = Intent(this@DetailsActivity,ImageActivity::class.java)
                intent.putExtra("url",url)
                startActivity(intent)
            }
        }
    }

    fun onClickSaveAlbum(view: View) {
        val disposable2 = ApiFactory.apiService.getAlbumAll()
            .subscribeOn(Schedulers.io())
            .subscribe({
                viewModel.insertAlbum(album)
                viewModel.insertAlbumDetails(albumDetailsList)
                finish()
            }, {
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable2)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}