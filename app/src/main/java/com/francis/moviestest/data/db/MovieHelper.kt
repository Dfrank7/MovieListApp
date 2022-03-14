package com.francis.moviestest.data.db

import androidx.lifecycle.LiveData
import com.francis.moviestest.home.model.domain.NetworkMoviesContainer
import com.francis.moviestest.home.model.domain.toPopulatEntity
import com.francis.moviestest.home.model.domain.toTopEntity
import com.francis.moviestest.home.model.domain.toUpcomingEntity

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

    fun getTopList(): LiveData<List<TopRatedMovieData>>{
        return database.getMoviesDb().getTopMovies()
    }

    fun  saveTopList(data: NetworkMoviesContainer){
        database.getMoviesDb().saveTopMovies(*data.toTopEntity())
    }

}