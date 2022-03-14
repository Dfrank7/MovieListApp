package com.francis.moviestest.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.francis.moviestest.data.db.MoviesDb
import com.francis.moviestest.data.db.PopularMovieData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Unit test the DAO
@SmallTest
class MovieDaoTest {

    @get:Rule
    var instantexecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MoviesDb

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MoviesDb::class.java
        ).build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertMovies_RetrieveFromDb_BothareSame() = runBlockingTest {
        val movie = PopularMovieData(false, "test", 1, "test", "test",
        "test", 3.0,"Pix", "2022","TEST", false, 3.45, 4)
        //Saving data to database
        //Retrieving data from database
        val dataFromSource = database.getMoviesDb().getPopularMoviesTest()

        println(movie.overview + "and "+ dataFromSource.overview)


        MatcherAssert.assertThat(dataFromSource?.overview, `is`(movie.overview))
        MatcherAssert.assertThat(dataFromSource?.popularity, `is`(movie.popularity))

    }

}