package com.enesseval.moviedeneme.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.enesseval.moviedeneme.R
import com.enesseval.moviedeneme.model.FirstModel2
import com.enesseval.moviedeneme.model.Movie
import com.enesseval.moviedeneme.service.MovieAPI
import com.enesseval.moviedeneme.util.downloadImageUrl
import com.enesseval.moviedeneme.util.placeholderProgressBar
import com.enesseval.moviedeneme.viewmodel.MovieDetailViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_movies_detail.*
import kotlinx.android.synthetic.main.movie_row.*
import okhttp3.OkHttpClient
import okhttp3.internal.wait
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MoviesDetailFragment : Fragment() {

    private lateinit var detailViewModel : MovieDetailViewModel
    private var movieId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.movie_details)

        arguments?.let {
            movieId = MoviesDetailFragmentArgs.fromBundle(it).movieId
        }

        detailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        detailViewModel.loadData(movieId)

        /*val loggingInterceptor : HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient : OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

        val BASE_URL = "https://yts.mx/"
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MovieAPI::class.java)

        fun getMovie(movieId: Int) : Observable<FirstModel2>{
            return api.getMovie(movieId)
        }*/


        observeLiveData(context)
    }
    fun observeLiveData(context: Context?){
        detailViewModel.oneMovie.observe(viewLifecycleOwner, Observer {movie ->
            movie?.let {
                mdMovieName.text = movie.movieName
                mdMovieYear.text = movie.movieYear.toString()
                mdMovieLanguage.text = movie.movieLang
                mdMovieUrl.text = movie.movieUrl
                mdMovieSum.text = movie.movieSum
                mdMovieRate.text = movie.movieRate.toString()
                context?.let {
                    mdMovieImage.downloadImageUrl(movie.movieImage, placeholderProgressBar(context))
                }
            }
        })
    }
}
