package com.francis.moviestest.data.repository

import androidx.lifecycle.LiveData
import com.francis.moviestest.MoviesResponse
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.domain.NetworkMoviesContainer

interface IMoviesRepository {

    fun getRemotePopularMovies(successCallback: (MoviesResponse)-> Unit, errorCallback: (String)-> Unit)

    fun getSavedPopularList(): LiveData<List<PopularMovieData>>

    fun savePopularList(popularMovieData: NetworkMoviesContainer)
}