package com.enesseval.moviedeneme.service

import com.enesseval.moviedeneme.model.FirstModel
import com.enesseval.moviedeneme.model.FirstModel2
import com.enesseval.moviedeneme.model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *Created by eness on 28.05.2020.
 */
interface MovieAPI {

    @GET("api/v2/list_movies.json?sort_by=date_added&limit=50")
    fun getMovies(): Observable<FirstModel>

    @GET("api/v2/movie_details.json")
    fun getMovie(@Query("movie_id")movie_id : Int) : Observable<FirstModel2>

}