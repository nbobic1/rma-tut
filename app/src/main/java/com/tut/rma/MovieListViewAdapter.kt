package com.tut.rma

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
        return k.get(k.indexOf(m)).accter
    }

    fun getSimilars(m:Movie ):List<String>
    {
        var k= MovieRepository.getFavoriteMovies()
        k.toMutableList().addAll(MovieRepository.getRecentMovies());
        return k.get(k.indexOf(m)).similar
    }
}
