package com.tut.rma

import java.io.Serializable

data class Movie
    (var id: Long,
     var title: String,
     var overview: String,
     var releaseDate: String,
     var homepage: String?,
     var genre: String?,
     var accter:List<String>,
     var similar:List<String>,
     var posterPath:String,
     var backdropPath:String?
      ):Serializable
