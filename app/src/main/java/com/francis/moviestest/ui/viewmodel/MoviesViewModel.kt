package com.francis.moviestest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.francis.moviestest.model.MoviesResponse
import com.francis.moviestest.model.Movie
import com.francis.moviestest.base.BaseViewModel
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.domain.PopularMoviesContainer
import com.francis.moviestest.data.domain.UpcomingMoviesContainer
import com.francis.moviestest.data.domain.toMovie
import com.francis.moviestest.data.repository.IMoviesRepository
import kotlinx.coroutines.launch


class MoviesViewModel(
    private val moviesRepository: IMoviesRepository
): BaseViewModel<IMoviesRepository>(moviesRepository) {

    private val _status = MutableLiveData<MovieAPIStatus>()
    val status: LiveData<MovieAPIStatus> get() = _status

    private val movieoption = MutableLiveData(MOVIESSOPTION.POPULAR)

    private val _movieList = MutableLiveData<List<Movie>>()
    private lateinit var movies : LiveData<List<Movie>>

    private val _navigateToDetails = MutableLiveData<Movie?>()
    val navigateToDetails:LiveData<Movie?>
        get() = _navigateToDetails

    init {
        viewModelScope.launch {
            try {
                _status.value = MovieAPIStatus.LOADING
                getRemotePopularMovies()
                getRemoteUpcomingMovies()
            }catch (e:Exception){
                _status.value = MovieAPIStatus.ERROR
            }finally {
                _status.value = MovieAPIStatus.DONE
            }
        }
    }



    fun getRemotePopularMovies(){
        viewModelScope.launch {
            moviesRepository.getRemotePopularMovies(
                successCallback = {
                    _movieList.postValue(it.results)
                },
                errorCallback = {

                }
            )
        }
    }

    fun getRemoteUpcomingMovies(){
        viewModelScope.launch {
            moviesRepository.getRemoteUpcomingMovies(
                successCallback = {
                    _movieList.postValue(it.results)
                },
                errorCallback = {

                }
            )
        }
    }

    fun getTopRatedMovies(){

    }

    fun setMoviesOption(moviesOption: MOVIESSOPTION){
        movieoption.value = moviesOption
    }


    fun onMovieClicked(movie: Movie) {
        _navigateToDetails.value = movie
    }

    fun onMovieCompleteNavigation(){
        _navigateToDetails.value = null
    }

    val movieList = Transformations.switchMap(movieoption){
        when(it){
            MOVIESSOPTION.POPULAR -> getSavedPopularMovies()
            MOVIESSOPTION.UPCOMING -> getSavedUpcomingMovies()
        }
    }

    fun getSavedPopularMovies(): LiveData<List<Movie>>{
        viewModelScope.launch {
            val movi= moviesRepository.getSavedPopularList()
            movies = Transformations.map(movi){
                PopularMoviesContainer(it).toMovie()
            }
        }
        return movies
    }

    fun getSavedUpcomingMovies(): LiveData<List<Movie>>{
        viewModelScope.launch {
            val movi= moviesRepository.getSavedUpcomingList()
            movies = Transformations.map(movi){
                UpcomingMoviesContainer(it).toMovie()
            }
        }
        return movies
    }

    enum class MOVIESSOPTION{
        POPULAR, UPCOMING
    }

    enum class MovieAPIStatus {
        LOADING,
        ERROR,
        DONE
    }
}