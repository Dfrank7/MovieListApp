package com.francis.moviestest.data.db

import androidx.lifecycle.LiveData
import com.francis.moviestest.data.domain.NetworkMoviesContainer
import com.francis.moviestest.data.domain.toPopulatEntity

class MovieHelper(private val database: MoviesDb) {

    fun getPopularList(): LiveData<List<PopularMovieData>>{
        return database.getMoviesDb().getPopularMovies()
    }

    fun  savePopularList(data: NetworkMoviesContainer){
        database.getMoviesDb().savePopularMovies(*data.toPopulatEntity())
    }

}