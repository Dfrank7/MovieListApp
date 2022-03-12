package com.francis.moviestest.data.local

import com.francis.moviestest.MoviesResponse
import com.francis.moviestest.data.IDataSource

interface IMoviesLocalDatasource : IDataSource{
    fun getPopularMovies(name: String): List<MoviesResponse>
    fun savePopularMovies(movies: List<MoviesResponse>)
}