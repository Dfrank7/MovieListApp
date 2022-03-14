package com.francis.moviestest.data.db

import androidx.lifecycle.LiveData
import com.francis.moviestest.data.domain.NetworkMoviesContainer
import com.francis.moviestest.data.domain.toPopulatEntity
import com.francis.moviestest.data.domain.toUpcomingEntity

class MovieHelper(private val database: MoviesDb) {

    fun getPopularList(): LiveData<List<PopularMovieData>>{
        return database.getMoviesDb().getPopularMovies()
    }

    fun  savePopularList(data: NetworkMoviesContainer){
        database.getMoviesDb().savePopularMovies(*data.toPopulatEntity())
    }

    fun getUpcomingList(): LiveData<List<UpcomingMovieData>>{
        return database.getMoviesDb().getUpcomingMovies()
    }

    fun  saveUpcomingList(data: NetworkMoviesContainer){
        database.getMoviesDb().saveUpcomingMovies(*data.toUpcomingEntity())
    }

}