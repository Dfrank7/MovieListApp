package com.francis.moviestest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePopularMovies(vararg movieTrans: PopularMovieData)

    @Query("Select * From PopularMovieData")
    fun getPopularMovies(): LiveData<List<PopularMovieData>>
}