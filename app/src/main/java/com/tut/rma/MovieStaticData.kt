package com.tut.rma

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object MovieRepository {
    suspend fun getUpcomingMovies(
    ) : GetMoviesResponse?{
        return withContext(Dispatchers.IO) {
            var response = ApiAdapter.retrofit.getUpcomingMovies()
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
    fun getFavoriteMovies() : List<Movie> {
        return favoriteMovies();
    }
    fun getRecentMovies() : List<Movie> {
        return recentMovies();
    }
    suspend fun searchRequest(
        query: String
    ): Result<List<Movie>>{
        return withContext(Dispatchers.IO) {
            try {
                val movies = arrayListOf<Movie>()
                var tmdb_api_key="574fa1986673102f483efa843989bba6"
                val url1 = "https://api.themoviedb.org/3/search/movie?api_key=$tmdb_api_key&query=$query" //1
                val url = URL(url1) //2
                (url.openConnection() as? HttpURLConnection)?.run { //3
                    val result = this.inputStream.bufferedReader().use { it.readText() } //4
                    val jo = JSONObject(result)//5
                    val results = jo.getJSONArray("results")//6
                    for (i in 0 until results.length()) {//7
                        val movie = results.getJSONObject(i)
                        val title = movie.getString("title")
                        val id = movie.getInt("id")
                        val posterPath = movie.getString("poster_path")
                        val overview = movie.getString("overview")
                        val releaseDate = movie.getString("release_date")
                        movies.add(Movie(id.toLong(), title, overview, releaseDate, null, null,null))

                        if (i == 5) break
                    }
                }
                return@withContext Result.Success(movies);//8
            }
            catch (e: MalformedURLException) {
                return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
            } catch (e: IOException) {
                return@withContext Result.Error(Exception("Cannot read stream"))
            } catch (e: JSONException) {
                return@withContext Result.Error(Exception("Cannot parse JSON"))
            }
        }
    }

suspend fun searchRequest1(
    ides:String
): Result<List<Movie>>{
    return withContext(Dispatchers.IO) {
        try {
            val movies = arrayListOf<Movie>()
            var tmdb_api_key="574fa1986673102f483efa843989bba6"
            val url1 = "https://api.themoviedb.org/3/movie/$ides/similar?api_key=$tmdb_api_key&language=en-US" //1
            val url = URL(url1) //2
            (url.openConnection() as? HttpURLConnection)?.run { //3
                val result = this.inputStream.bufferedReader().use { it.readText() } //4
                val jo = JSONObject(result)//5
                val results = jo.getJSONArray("results")//6
                println("ovo je duzin==${results.length()}")
                for (i in 0 until results.length()) {//7
                    val movie = results.getJSONObject(i)
                    val title = movie.getString("title")
                    val id = movie.getInt("id")
                    val posterPath = movie.getString("poster_path")
                    val overview = movie.getString("overview")
                    val releaseDate = movie.getString("release_date")
                    movies.add(Movie(id.toLong(), title, overview, releaseDate, null, posterPath,null))

                    if (i == 5) break
                }
            }
            return@withContext Result.Success(movies);//8
        }
        catch (e: MalformedURLException) {
            return@withContext Result.Error(Exception("Cannot open HttpURLConnection"))
        } catch (e: IOException) {
            return@withContext Result.Error(Exception("Cannot read stream"))
        } catch (e: JSONException) {
            return@withContext Result.Error(Exception("Cannot parse JSON"))
        }
    }
}
}

fun favoriteMovies(): List<Movie> {
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
        "16.02.2005.","https://www.imdb.com/title/tt0414387/",
        "a",null),Movie(2,"Moja iflm",
            "Sparks fly when spieets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2003.","https://www.imdb.com/title/tt0414387/",
            "c",null),Movie(3,"Pirati s kariva",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "a",null),Movie(4,"Moja zena",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "d",null)
//Dodajte filmove po želji
    )
}
fun recentMovies(): List<Movie> {
    return listOf(
        Movie(1,"The Contractor",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "d",null),
        Movie(1,"Radi sve",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "b",null),
        Movie(1,"Pulp Fiction",
            "A discharged U.S. Special Forces sergeant, James Harper,pair of diner bandits, " +
                    "risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "crime",null)
//Dodajte filmove po želji
    )
}