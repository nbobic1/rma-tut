package com.tut.rma

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Pair as UtilPair
class RecentMoviesFragment : Fragment() {
    private lateinit var favoriteMovies: RecyclerView
    private lateinit var favoriteMoviesAdapter: MovieListAdapter
    fun onSuccess(movies:List<Movie>){
        val toast = Toast.makeText(context, "Upcoming movies found", Toast.LENGTH_SHORT)
        toast.show()
        favoriteMoviesAdapter.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
    private var movieListViewModel =  MovieListViewModel(null,null)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.recent_fragment, container, false)
        favoriteMovies = view.findViewById(R.id.favoriteMovies)
        favoriteMovies.layoutManager = GridLayoutManager(activity, 2)
        favoriteMoviesAdapter = MovieListAdapter(arrayListOf(),true) { movie,view,view2 -> showMovieDetails(movie,view,view2) }
        favoriteMovies.adapter=favoriteMoviesAdapter
        favoriteMoviesAdapter.updateMovies(movieListViewModel.getRecentMovies())
        println("faklčjjjfjfjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj")
        movieListViewModel.getUpcoming( onSuccess = ::onSuccess, onError = ::onError )
        return view;
    }
    companion object {
        fun newInstance(): RecentMoviesFragment = RecentMoviesFragment()
    }private fun showMovieDetails(movie: Movie, view1: View,view2:View) {
        val intent = Intent(activity, MainActivity2::class.java).apply {
            putExtra("movie_title", movie.title)
            putExtra("movie",movie)
        }
        val options = ActivityOptions
            .makeSceneTransitionAnimation(activity,  UtilPair.create(view1, "poster"),
                UtilPair.create(view2, "title"))
        startActivity(intent, options.toBundle())
    }
}