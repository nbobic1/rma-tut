package com.tut.rma

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDetailViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun writeDB(context: Context, movie:Movie, onSuccess: (movies: String) -> Unit,
                onError: () -> Unit){

        scope.launch{
            val result = MovieRepository.writeFavorite(context,movie)
            when (result) {
                is String -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }
    fun getMovieByTitle(name:String):Movie{
        var movies: ArrayList<Movie> = arrayListOf()
        movies.addAll(MovieRepository.getRecentMovies())
        movies.addAll(MovieRepository.getFavoriteMovies())
        val movie= movies.find { movie -> name.equals(movie.title) }
        //ako film ne postoji vratimo testni
        return movie?:Movie(0,"Test","Test","Test","Test","Test",null)
    }
}