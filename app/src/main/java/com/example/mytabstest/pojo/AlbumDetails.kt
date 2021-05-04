package com.example.mytabstest.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

//POJO
@Entity(tableName = "album_details_list")
data class AlbumDetails (
    @SerializedName("albumId")
    @Expose
    val albumId: Int? = null,

    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl: String? = null
)