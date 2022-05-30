package com.tut.rma

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable
@Entity
data class Movie
    ( @ColumnInfo(name = "id") @PrimaryKey @SerializedName("id") var id: Long,
     @ColumnInfo(name = "title") @SerializedName("title")  var title: String,
     @ColumnInfo(name = "overview") @SerializedName("overview")  var overview: String,
     @ColumnInfo(name = "release_date") @SerializedName("release_date")   var releaseDate: String,
     @ColumnInfo(name = "homepage") @SerializedName("homepage")   var homepage: String?,
     @ColumnInfo(name = "poster_path") @SerializedName("poster_path") var posterPath: String?,
     @ColumnInfo(name = "backdrop_path") @SerializedName("backdrop_path")  var backdropPath: String?
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