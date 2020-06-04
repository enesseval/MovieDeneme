package com.enesseval.moviedeneme.service

import com.enesseval.moviedeneme.model.FirstModel
import com.enesseval.moviedeneme.model.FirstModel2
import com.enesseval.moviedeneme.model.Movie
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *Created by eness on 28.05.2020.
 */

class MovieAPIService() {



    private val BASE_URL = "https://yts.mx/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MovieAPI::class.java)

    fun getData() : Observable<FirstModel> {
        return api.getMovies()
    }
    fun getMovie(movieId : Int) : Observable<FirstModel2>{
        return api.getMovie(movieId)
    }
}