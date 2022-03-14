package com.francis.moviestest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePopularMovies(vararg movieTrans: PopularMovieData)

    @Query("Select * From PopularMovieData")
    fun getPopularMovies(): LiveData<List<PopularMovieData>>

//    @Q
//    fun deleteAll()

    @Query("Select * From PopularMovieData")
    fun getPopularMoviesTest(): PopularMovieData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUpcomingMovies(vararg movieTrans: UpcomingMovieData)

    @Query("Select * From UpcomingMovieData")
    fun getUpcomingMovies(): LiveData<List<UpcomingMovieData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTopMovies(vararg movieTrans: TopRatedMovieData)

    @Query("Select * From TopRatedMovieData")
    fun getTopMovies(): LiveData<List<TopRatedMovieData>>
}