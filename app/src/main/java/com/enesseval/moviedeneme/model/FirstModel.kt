package com.enesseval.moviedeneme.model

import com.google.gson.annotations.SerializedName

/**
 *Created by eness on 29.05.2020.
 */
data class FirstModel(
    @SerializedName("data")
    val data : Movies
) {


}
