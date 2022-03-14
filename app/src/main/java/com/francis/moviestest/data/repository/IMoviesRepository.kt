package com.francis.moviestest.data.repository

import androidx.lifecycle.LiveData
import com.francis.moviestest.model.MoviesResponse
import com.francis.moviestest.base.IBaseRepository
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.db.TopRatedMovieData
import com.francis.moviestest.data.db.UpcomingMovieData
import com.francis.moviestest.data.domain.NetworkMoviesContainer

interface IMoviesRepository : IBaseRepository {

    fun getRemotePopularMovies(successCallback: (MoviesResponse)-> Unit, errorCallback: (String)-> Unit)

    fun getSavedPopularList(): LiveData<List<PopularMovieData>>

    fun savePopularList(popularMovieData: NetworkMoviesContainer)

    fun getRemoteUpcomingMovies(successCallback: (MoviesResponse)-> Unit, errorCallback: (String)-> Unit)

    fun getSavedUpcomingList(): LiveData<List<UpcomingMovieData>>

    fun saveUpcomingList(upcomingMovieData: NetworkMoviesContainer)

    fun getRemoteTopMovies(successCallback: (MoviesResponse)-> Unit, errorCallback: (String)-> Unit)

    fun getSavedTopList(): LiveData<List<TopRatedMovieData>>

    fun saveTopList(topRatedMovieData: NetworkMoviesContainer)

}