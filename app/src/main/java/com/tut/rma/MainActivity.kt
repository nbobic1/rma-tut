package com.tut.rma

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val br: BroadcastReceiver = Brodcast()
    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
    }
    private lateinit var favoriteMovies: RecyclerView
    private lateinit var favoriteMoviesAdapter: MovieListAdapter
    private lateinit var recomMovies : RecyclerView
    private lateinit var recomMoviesAdapter: MovieListAdapter
    private var movieListViewModel = MovieListViewModel()
    private lateinit var searchText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
        registerReceiver(br, filter)
        searchText=findViewById(R.id.editTextTextPersonName)
        if(intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain")
            handleSendText(intent)
        favoriteMovies = findViewById(R.id.recyclerView2)
        recomMovies=findViewById(R.id.recyclerView)
        recomMovies.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        favoriteMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        favoriteMoviesAdapter = MovieListAdapter(listOf()) { movie -> showMovieDetails(movie) }
        favoriteMovies.adapter = favoriteMoviesAdapter
        favoriteMoviesAdapter.updateMovies(movieListViewModel.getFavoriteMovies())

        recomMoviesAdapter = MovieListAdapter(listOf()) { movie -> showMovieDetails(movie) }
        recomMovies.adapter = recomMoviesAdapter
        recomMoviesAdapter.updateMovies(movieListViewModel.getRecentMovies())
}

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }
    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
           searchText.setText(it)
        }
    }
    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("movie_title", movie.title)
        }
        startActivity(intent)
    }

}