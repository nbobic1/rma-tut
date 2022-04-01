package com.tut.rma

import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.beans.HasPropertyWithValue.hasProperty
import org.hamcrest.CoreMatchers.`is` as Is
import org.hamcrest.core.IsIterableContaining.hasItem
import org.hamcrest.core.IsNot.not
import org.junit.Test

class RepositoryUnitTest {
    @Test
    fun testGetFavoriteMovies(){
        val movies = MovieRepository.getFavoriteMovies()
        assertEquals(movies.size,4)
        assertThat(movies, hasItem<Movie>(hasProperty("title", Is("Pride and prejudice"))))
        assertThat(movies, not(hasItem<Movie>(hasProperty("title", Is("Pirati dsgsdgsgs kariva")))))
    }
}