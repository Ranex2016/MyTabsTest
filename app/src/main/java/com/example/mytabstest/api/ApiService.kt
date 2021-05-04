package com.example.mytabstest.api

import com.example.mytabstest.pojo.AlbumDetails
import com.example.mytabstest.pojo.Album
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("albums")
    fun getAlbumAll(): Single<List<Album>>

    @GET("photos")
    fun getAlbumDetails(@Query(QUERY_PARAM_ALBUM_ID) albumId: Int = 1): Single<List<AlbumDetails>>

    companion object {
        private const val QUERY_PARAM_ALBUM_ID = "albumId"
    }
}