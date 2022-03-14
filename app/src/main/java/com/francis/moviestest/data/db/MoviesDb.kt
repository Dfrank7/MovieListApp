package com.francis.moviestest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PopularMovieData::class, UpcomingMovieData::class], version = 1)
abstract class MoviesDb : RoomDatabase() {

    abstract fun getMoviesDb(): MoviesDao

    companion object {
        @Volatile
        var INSTANCE: MoviesDb? = null

        fun getInstance(context: Context): MoviesDb {

            val tempInstance = INSTANCE

            if (tempInstance != null) {

                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(context.applicationContext,
                    MoviesDb::class.java, "movies_db")
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance

                return instance
            }
        }


    }

}