package com.francis.moviestest.data.local

import androidx.lifecycle.LiveData
import com.francis.moviestest.data.IDataSource
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.db.UpcomingMovieData
import com.francis.moviestest.data.domain.NetworkMoviesContainer

interface IMoviesLocalDatasource : IDataSource{
    fun getPopularMovies(): LiveData<List<PopularMovieData>>
    fun savePopularMovies(movies: NetworkMoviesContainer)
    fun getUpcomingMovies(): LiveData<List<UpcomingMovieData>>
    fun saveUpcomingMovies(movies: NetworkMoviesContainer)
}