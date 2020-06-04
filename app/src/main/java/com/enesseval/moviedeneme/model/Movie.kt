package com.enesseval.moviedeneme.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *Created by eness on 26.05.2020.
 */
@Entity
data class Movie(
      @ColumnInfo(name = "movieApiId")
      @SerializedName("id")
      val movieApiId : Int,
      @ColumnInfo(name = "movieUrl")
      @SerializedName("url")
      val movieUrl : String,
      @ColumnInfo(name = "movieImdbCode")
      @SerializedName("imdb_code")
      val movieImdbCode : String,
      @ColumnInfo(name = "movieName")
      @SerializedName("title")
      val movieName : String,
      @ColumnInfo(name = "movieYear")
      @SerializedName("year")
      val movieYear : Int,
      @ColumnInfo(name = "movieRate")
      @SerializedName("rating")
      val movieRate : Double,
      @SerializedName("genres")
      val movieGenre : List<String>,
      @ColumnInfo(name = "movieSum")
      @SerializedName("description_full")
      val movieSum : String,
      @ColumnInfo(name = "movieImage")
      @SerializedName("medium_cover_image")
      val movieImage : String,
      @ColumnInfo(name = "movieLang")
      @SerializedName("language")
      val movieLang : String){
      @PrimaryKey(autoGenerate = true)
      var uuid : Int = 0
      var genre = ""
}