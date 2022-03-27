package com.tut.rma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var favoriteMovies: RecyclerView
    private lateinit var favoriteMoviesAdapter: MovieListAdapter
    private lateinit var recomMovies : RecyclerView
    private lateinit var recomMoviesAdapter: MovieListAdapter
    private var movieListViewModel = MovieListViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        favoriteMovies = findViewById(R.id.recyclerView2)
        recomMovies=findViewById(R.id.recyclerView)
        recomMovies.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        favoriteMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        favoriteMoviesAdapter = MovieListAdapter(listOf())
        favoriteMovies.adapter = favoriteMoviesAdapter
        favoriteMoviesAdapter.updateMovies(movieListViewModel.getFavoriteMovies())

        recomMoviesAdapter = MovieListAdapter(listOf())
        recomMovies.adapter = recomMoviesAdapter
        recomMoviesAdapter.updateMovies(movieListViewModel.getRecentMovies())
}
    }