package com.francis.moviestest.data.domain

import com.francis.moviestest.Result
import com.francis.moviestest.data.db.PopularMovieData

//fun Result.toPopularEntity() = PopularMovieData(
//    adult, backdrop_path, genre_ids, id, original_language, original_title, overview, popularity, poster_path, release_date, title, video, vote_average, vote_count
//)

data class NetworkMoviesContainer(val result: List<Result>)

fun NetworkMoviesContainer.toPopulatEntity(): Array<PopularMovieData>{
    return result.map {
        PopularMovieData(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids,
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