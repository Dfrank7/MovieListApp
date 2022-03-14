package com.francis.moviestest.data.domain

import com.francis.moviestest.model.Movie
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.db.TopRatedMovieData
import com.francis.moviestest.data.db.UpcomingMovieData

data class NetworkMoviesContainer(val result: List<Movie>)
data class PopularMoviesContainer(val popularMovies: List<PopularMovieData>)
data class UpcomingMoviesContainer(val upcomingMovies: List<UpcomingMovieData>)
data class TopMoviesContainer(val topMovies: List<TopRatedMovieData>)

fun NetworkMoviesContainer.toPopulatEntity(): Array<PopularMovieData>{
    return result.map {
        PopularMovieData(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
//            genre_ids = it.genre_ids,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }.toTypedArray()
}

fun NetworkMoviesContainer.toUpcomingEntity(): Array<UpcomingMovieData>{
    return result.map {
        UpcomingMovieData(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
//            genre_ids = it.genre_ids,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }.toTypedArray()
}

fun NetworkMoviesContainer.toTopEntity(): Array<TopRatedMovieData>{
    return result.map {
        TopRatedMovieData(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
//            genre_ids = it.genre_ids,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }.toTypedArray()
}

fun PopularMoviesContainer.toMovie() : List<Movie>{
    return popularMovies.map {
        Movie(
            it.adult, it.backdrop_path, it.id, it.original_language, it.original_title, it.overview, it.popularity,
            it.poster_path, it.release_date, it.original_title, it.video, it.vote_average, it.vote_count
        )
    }
}

fun UpcomingMoviesContainer.toMovie() : List<Movie>{
    return upcomingMovies.map {
        Movie(
            it.adult, it.backdrop_path, it.id, it.original_language, it.original_title, it.overview, it.popularity,
            it.poster_path, it.release_date, it.original_title, it.video, it.vote_average, it.vote_count
        )
    }
}

fun TopMoviesContainer.toMovie() : List<Movie>{
    return topMovies.map {
        Movie(
            it.adult, it.backdrop_path, it.id, it.original_language, it.original_title, it.overview, it.popularity,
            it.poster_path, it.release_date, it.original_title, it.video, it.vote_average, it.vote_count
        )
    }
}