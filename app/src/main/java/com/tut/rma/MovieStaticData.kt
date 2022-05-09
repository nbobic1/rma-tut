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
                        movies.add(Movie(id.toLong(), title, overview, releaseDate, null, null,listOf(),
                            listOf(), posterPath))

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
                    movies.add(Movie(id.toLong(), title, overview, releaseDate, null, null,listOf(),
                        listOf(), posterPath))

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
        "a",listOf("kama","neko90*","reko9"),listOf("ia9","agaggaa9g","gagaga9","gagaassee9"),""),Movie(2,"Moja iflm",
            "Sparks fly when spieets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2003.","https://www.imdb.com/title/tt0414387/",
            "c",listOf("kama7","neko7" ,"reko7"),listOf("ia7","agaggaa7g","7gagaga","7gagaassee"),""),Movie(3,"Pirati s kariva",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "a",listOf("kama3","neko3","reko3"),listOf("ia3","agaggaag3","gagag3a","3gagaassee"),""),Movie(4,"Moja zena",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "d",listOf("kama4","ne4ko","4reko"),listOf("5ia","5agaggaag","g5agaga","5gagaassee"),"")
//Dodajte filmove po želji
    )
}
fun recentMovies(): List<Movie> {
    return listOf(
        Movie(1,"The Contractor",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "d",listOf("kama","neko","reko"),listOf("ia","agaggaag","gagaga","gagaassee"),""),
        Movie(1,"Radi sve",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "b",listOf("kama2","neko2","reko2"),listOf("ia2","agaggaag2","gagaga2","gagaassee2"),""),
        Movie(1,"Pulp Fiction",
            "A discharged U.S. Special Forces sergeant, James Harper,pair of diner bandits, " +
                    "risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "crime",listOf("kama3","neko3","reko3"),listOf("ia3","agaggaag3","gagaga3","gagaassee3"),"")
//Dodajte filmove po želji
    )
}