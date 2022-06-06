package com.tut.rma

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Pair as UtilPair
class FavoriteMoviesFragment : Fragment() {
        private lateinit var favoriteMovies: RecyclerView
        private lateinit var favoriteMoviesAdapter: MovieListAdapter
        private var movieListViewModel = context?.let { MovieListViewModel(it) }
    fun onSuccess(movies:List<Movie>){
        favoriteMoviesAdapter.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Error", Toast.LENGTH_SHORT)
        toast.show()
    }
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            var view =  inflater.inflate(R.layout.favorites_fragment, container, false)
            favoriteMovies = view.findViewById(R.id.favoriteMovies)
            favoriteMovies.layoutManager = GridLayoutManager(activity, 2)
            favoriteMoviesAdapter = MovieListAdapter(arrayListOf(),true) { movie,view,view2 -> showMovieDetails(movie,view,view2) }
            favoriteMovies.adapter=favoriteMoviesAdapter
            movieListViewModel?.let { favoriteMoviesAdapter.updateMovies(it.getFavoriteMovies()) }
            context?.let { movieListViewModel= MovieListViewModel(it) }
            val moviesObserver = Observer<List<Movie>> { movies ->
                favoriteMoviesAdapter.updateMovies(movies)
            }
            movieListViewModel?.favoriteMovies?.observe(viewLifecycleOwner, moviesObserver)
            return view;
        }
        companion object {
            fun newInstance(): FavoriteMoviesFragment = FavoriteMoviesFragment()
        }
    private fun showMovieDetails(movie: Movie, view1: View,view2:View) {
        val intent = Intent(activity, MainActivity2::class.java).apply {
            putExtra("movie_title", movie.title)
            putExtra("movie",Movie(-1,"","","","","",null))
        }
        val options = ActivityOptions
            .makeSceneTransitionAnimation(activity,  UtilPair.create(view1, "poster"),
                UtilPair.create(view2, "title"))
        startActivity(intent, options.toBundle())
    }
}