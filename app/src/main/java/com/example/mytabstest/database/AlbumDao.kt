package com.example.mytabstest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mytabstest.pojo.Album
import com.example.mytabstest.pojo.AlbumDetails

@Dao
interface AlbumDao {
    //Получим фотографии альбома из БД
    @Query("SELECT * FROM album_details_list WHERE albumId == :albumId")
    fun getAlbumDetailsList(albumId: Int): LiveData<List<AlbumDetails>>

    //Сохранение фотографий альбома в БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbumDetails(albumList: List<AlbumDetails>)

    @Query("SELECT * FROM album_all_list")
    fun getAlbumList(): LiveData<List<Album>>

    //Сохраняем информацию об альбоме в БД
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    //Сохраняем информацию об альбоме в БД
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insertAlbumList(albumList: List<Album>)

    //Очистим таблицу со всеми альбомами
    @Query("DELETE FROM album_all_list WHERE id ==:albumId")
    fun deleteAlbum(albumId: Int)

    //Очистим таблицу подробной информацией об альбомах
    @Query("DELETE FROM album_details_list WHERE albumId ==:albumId")
    fun deleteAlbumDetails(albumId: Int)

}