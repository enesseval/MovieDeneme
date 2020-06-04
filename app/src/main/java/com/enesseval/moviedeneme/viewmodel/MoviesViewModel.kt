package com.enesseval.moviedeneme.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.enesseval.moviedeneme.model.FirstModel
import com.enesseval.moviedeneme.model.Movie
import com.enesseval.moviedeneme.model.Movies
import com.enesseval.moviedeneme.service.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 *Created by eness on 26.05.2020.
 */
class MoviesViewModel(application: Application) : BaseViewModel(application) {

    private var movieModels: List<Movie>? = null

    private val movieAPIService = MovieAPIService()
    private val disposable = CompositeDisposable()

    val movies = MutableLiveData<List<Movie>>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getMovies()
    }

    @SuppressLint("CheckResult")
    private fun getMovies() {
        movieLoading.value = true

        disposable.add(movieAPIService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<FirstModel>() {
                override fun onComplete() {
                    movieLoading.value = false
                }

                override fun onNext(t: FirstModel) {
                    showMovies(t)
                }

                override fun onError(e: Throwable) {
                    movieError.value = true
                    println(e.localizedMessage)
                }

            }))

    }
    private fun showMovies(movieList : FirstModel){
        val movie: Movies = movieList.data
        movies.value = movie.movies
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}

