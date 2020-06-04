package com.enesseval.moviedeneme.viewmodel

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.enesseval.moviedeneme.model.*
import com.enesseval.moviedeneme.service.MovieAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *Created by eness on 31.05.2020.
 */
class MovieDetailViewModel(application: Application) : BaseViewModel(application) {
    private val movieAPIService = MovieAPIService()
    private val disposable = CompositeDisposable()
    val oneMovie = MutableLiveData<Movie>()

    fun loadData(id: Int){
        getMovie(id)
    }


    private fun getMovie(id: Int) {
        launch {
            disposable.add(movieAPIService.getMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<FirstModel2>(){
                    override fun onComplete() {

                    }

                    override fun onNext(t: FirstModel2) {
                        val movie : OneMovie = t.data
                        oneMovie.value = movie.movie
                        println(oneMovie.value)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                }))
        }
    }
}