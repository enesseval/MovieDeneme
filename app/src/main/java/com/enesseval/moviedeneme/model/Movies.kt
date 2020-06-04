package com.enesseval.moviedeneme.model

import com.google.gson.annotations.SerializedName

/**
 *Created by eness on 30.05.2020.
 */
data class Movies(
    @SerializedName("movies")
    val movies : List<Movie>
) {}