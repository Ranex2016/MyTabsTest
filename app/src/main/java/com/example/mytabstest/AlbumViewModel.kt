package com.example.mytabstest

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mytabstest.api.ApiFactory
import com.example.mytabstest.database.AppDatabase
import com.example.mytabstest.pojo.Album
import com.example.mytabstest.pojo.AlbumDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    //Список всех альбомов в БД
    val albumList = db.albumDetailsDao().getAlbumList()

    //Содержимое альбома по ИД из БД
    fun getAlbumDetailsList(albumId: Int):LiveData<List<AlbumDetails>> {
        return db.albumDetailsDao().getAlbumDetailsList(albumId)
    }

    private val compositeDisposable = CompositeDisposable()

    //Вставка альбома
    fun insertAlbum(album: Album) {
        db.albumDetailsDao().insertAlbum(album)
    }

    //Вставка содержимого Альбома
    fun insertAlbumDetails(albumDetails: List<AlbumDetails>) {
        db.albumDetailsDao().insertAlbumDetails(albumDetails)
    }

    fun deleteAlbum(albumId:Int){
        db.albumDetailsDao().deleteAlbum(albumId)
    }

    fun deleteAlbumDetails(albumId:Int){
        db.albumDetailsDao().deleteAlbumDetails(albumId)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}