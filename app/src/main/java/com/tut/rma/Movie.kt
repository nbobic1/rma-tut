package com.tut.rma

import java.io.Serializable

data class Movie
    (val id: Long,
     val title: String,
     val overview: String,
     val releaseDate: String,
     val homepage: String,
     val genre: String,
     val accter:List<String>,
     val similar:List<String>
      ):Serializable
