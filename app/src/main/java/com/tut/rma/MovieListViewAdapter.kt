package com.tut.rma

import android.content.Context
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
class MovieListViewModel (private val searchDone: ((movies: List<Movie>) -> Unit)?,
                          private val onError: (()->Unit)?
) {
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    fun getFavorites(context: Context, onSuccess: (movies: List<Movie>) -> Unit,
                     onError: () -> Unit){
        scope.launch{
            val result = MovieRepository.getFavoriteMovies(context)
            when (result) {
                is List<Movie> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun search(query: String){
        // Kreira se Coroutine na UI
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            val result = MovieRepository.searchRequest(query)
            // Prikaže se rezultat korisniku na glavnoj niti
            when (result) {
                is Result.Success<List<Movie>> -> searchDone?.invoke(result.data)
                else-> onError?.invoke()
            }
        }
    }
    fun getUpcoming( onSuccess: (movies: List<Movie>) -> Unit,
                     onError: () -> Unit){
        // Create a new coroutine on the UI thread
        scope.launch{
            // Make the network call and suspend execution until it finishes
            val result = MovieRepository.getUpcomingMovies()
            // Display result of the network request to the user
            when (result) {
                is GetMoviesResponse -> onSuccess?.invoke(result.movies)
                else-> onError?.invoke()
            }
        }
    }
    fun search1(ide:String){
        // Kreira se Coroutine na UI
        scope.launch{
            // Vrši se poziv servisa i suspendira se rutina dok se `withContext` ne završi
            println("searchhhhhhhhhhhhhhhhhhhh")
            val result = MovieRepository.searchRequest1(ide)
            // Prikaže se rezultat korisniku na glavnoj niti
            when (result) {
                is Result.Success<List<Movie>> -> searchDone?.invoke(result.data)
                else-> onError?.invoke()
            }
        }
    }

    fun getFavoriteMovies():List<Movie>{
        return MovieRepository.getFavoriteMovies();
    }
    fun getRecentMovies():List<Movie>{
        return MovieRepository.getRecentMovies();
    }
    fun getAccters(m:Movie ):List<String>
    {
        var k= MovieRepository.getFavoriteMovies()
        k.toMutableList().addAll(MovieRepository.getRecentMovies());
      //  return k.get(k.indexOf(m)).accter
        return listOf()
    }

    fun getSimilars(m:Movie ):List<String>
    {
        var k= MovieRepository.getFavoriteMovies()
        k.toMutableList().addAll(MovieRepository.getRecentMovies());
        //return k.get(k.indexOf(m)).similar
        return listOf()
    }
}
