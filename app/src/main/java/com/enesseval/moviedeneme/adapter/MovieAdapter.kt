package com.enesseval.moviedeneme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.enesseval.moviedeneme.R
import com.enesseval.moviedeneme.model.Movie
import com.enesseval.moviedeneme.util.downloadImageUrl
import com.enesseval.moviedeneme.util.placeholderProgressBar
import com.enesseval.moviedeneme.view.MoviesFragment
import com.enesseval.moviedeneme.view.MoviesFragmentDirections
import kotlinx.android.synthetic.main.movie_row.view.*

/**
 *Created by eness on 26.05.2020.
 */
class MovieAdapter(val movieList: ArrayList<Movie>):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_row,parent,false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movieName.text = movieList[position].movieName
        holder.view.movieRate.text = movieList[position].movieRate.toString()
        var genre = ""
        /*for (a in movieList[position].movieGenre){
            genre += a
            genre += " "
        }
        holder.view.movieGenre.text = genre*/
        //holder.view.movieGenre.text = movieList[position].movieGenre.toString()
        holder.view.movieYear.text = movieList[position].movieYear.toString()
        holder.view.movieImage.downloadImageUrl(movieList[position].movieImage, placeholderProgressBar(holder.view.context))
        holder.view.movieId.text = movieList[position].movieApiId.toString()

        holder.view.setOnClickListener {
            val action = MoviesFragmentDirections.actionMoviesFragmentToMoviesDetailFragment(Integer.valueOf(movieList[position].movieApiId))
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateMovieList(newMovieList: List<Movie>){
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }
}