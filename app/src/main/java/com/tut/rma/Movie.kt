package com.tut.rma

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable

data class Movie
    (@SerializedName("id") var id: Long,
     @SerializedName("title")  var title: String,
     @SerializedName("overview")  var overview: String,
     @SerializedName("release_date")   var releaseDate: String,
     @SerializedName("homepage")   var homepage: String?,
     @SerializedName("poster_path") var posterPath: String?,
     @SerializedName("backdrop_path")  var backdropPath: String?
      ):Serializable
data class GetMoviesResponse(
 @SerializedName("page") val page: Int,
 @SerializedName("results") val movies: List<Movie>,
 @SerializedName("total_pages") val pages: Int
)
interface Api {
 @GET("movie/upcoming")
 suspend fun getUpcomingMovies(
  @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
 ): Response<GetMoviesResponse>
}
object ApiAdapter {
 val retrofit : Api = Retrofit.Builder()
  .baseUrl("https://api.themoviedb.org/3/")
  .addConverterFactory(GsonConverterFactory.create())
  .build()
  .create(Api::class.java)
}