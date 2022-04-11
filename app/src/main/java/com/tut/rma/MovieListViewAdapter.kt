package com.tut.rma

class MovieListViewModel {
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
