package com.example.mytabstest.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

//POJO
@Entity(tableName = "album_all_list")
data class Album(
    @SerializedName("userId")
    @Expose
    var userId: Int? = null,

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null
)