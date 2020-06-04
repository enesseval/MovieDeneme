package com.enesseval.moviedeneme.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesseval.moviedeneme.R
import com.enesseval.moviedeneme.adapter.MovieAdapter
import com.enesseval.moviedeneme.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.movie_row.*

class MoviesFragment : Fragment() {

    private lateinit var viewModel : MoviesViewModel
    private val movieAdapter = MovieAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.movies)

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.refreshData()

        movieList.layoutManager = LinearLayoutManager(context)
        movieList.adapter = movieAdapter

        swipeRefreshLayout.setOnRefreshListener {
            movieList.visibility = View.GONE
            movieError.visibility = View.GONE
            movieLoading.visibility = View.GONE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }



        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.movies.observe(viewLifecycleOwner, Observer {movies ->
            movies?.let {
                movieList.visibility = View.VISIBLE
                movieAdapter.updateMovieList(movies)
                movieError.visibility = View.GONE
                movieLoading.visibility = View.GONE

            }
        })
        viewModel.movieError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if (it){
                    movieList.visibility = View.GONE
                    movieError.visibility = View.VISIBLE
                    movieLoading.visibility = View.GONE
                }else{
                    movieError.visibility = View.GONE
                }
            }
        })
        viewModel.movieLoading.observe(viewLifecycleOwner, Observer {loading ->
            loading?.let {
                if(loading){
                    movieList.visibility = View.GONE
                    movieError.visibility = View.GONE
                    movieLoading.visibility = View.VISIBLE
                }else{
                    movieLoading.visibility = View.GONE
                }
            }
        })
    }
}
