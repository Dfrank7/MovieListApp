package com.francis.moviestest.data.local

import androidx.lifecycle.LiveData
import com.francis.moviestest.data.IDataSource
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.db.TopRatedMovieData
import com.francis.moviestest.data.db.UpcomingMovieData
import com.francis.moviestest.home.model.domain.NetworkMoviesContainer

interface IMoviesLocalDatasource : IDataSource{
    fun getPopularMovies(): LiveData<List<PopularMovieData>>
    fun savePopularMovies(movies: NetworkMoviesContainer)
    fun deletePopular()
    fun getUpcomingMovies(): LiveData<List<UpcomingMovieData>>
    fun saveUpcomingMovies(movies: NetworkMoviesContainer)
    fun deleteUpcoming()
    fun getTopMovies(): LiveData<List<TopRatedMovieData>>
    fun saveTopMovies(movies: NetworkMoviesContainer)
    fun deleteTop()
}