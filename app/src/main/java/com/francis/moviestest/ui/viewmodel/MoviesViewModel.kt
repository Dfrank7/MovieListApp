package com.francis.moviestest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.francis.moviestest.model.MoviesResponse
import com.francis.moviestest.model.Movie
import com.francis.moviestest.base.BaseViewModel
import com.francis.moviestest.data.db.PopularMovieData
import com.francis.moviestest.data.domain.PopularMoviesContainer
import com.francis.moviestest.data.domain.TopMoviesContainer
import com.francis.moviestest.data.domain.UpcomingMoviesContainer
import com.francis.moviestest.data.domain.toMovie
import com.francis.moviestest.data.repository.IMoviesRepository
import com.francis.moviestest.model.Response
import com.francis.moviestest.utility.INetworkStatus
import com.francis.moviestest.utility.NetworkStatus
import com.francis.moviestest.utility.isConnectionAvailable
import kotlinx.coroutines.launch


class MoviesViewModel(
    private val moviesRepository: IMoviesRepository,
    private val networkStatus: INetworkStatus
): BaseViewModel<IMoviesRepository>(moviesRepository) {

    private val _status = MutableLiveData<MovieAPIStatus>()
    val status: LiveData<MovieAPIStatus> get() = _status

    private val _movieoption = MutableLiveData(MOVIESSOPTION.POPULAR)
    val movieoption : LiveData<MOVIESSOPTION>
    get() = _movieoption

    private val _movieList = MutableLiveData<List<Movie>>()
    val refreshmovieList : LiveData<List<Movie>>
    get() = _movieList
    private lateinit var movies : LiveData<List<Movie>>

    private val _navigateToDetails = MutableLiveData<Movie>()
    val navigateToDetails:LiveData<Movie>
        get() = _navigateToDetails

    private val _checkInternet = MutableLiveData<Boolean>()
    val checkInternet : LiveData<Boolean>
        get() = _checkInternet

    init {
        viewModelScope.launch {
            try {
                _status.value = MovieAPIStatus.LOADING
                showLoading.value = true
                refreshMovies()
            }catch (e:Exception){
                _status.value = MovieAPIStatus.ERROR
                showLoading.value = false
            }finally {
                _status.value = MovieAPIStatus.DONE
                showLoading.value = false
            }
        }
        viewModelScope.launch {
            _checkInternet.value = networkStatus.isConnected()
        }
    }


    fun refreshMovies(){
        getRemotePopularMovies()
        getRemoteUpcomingMovies()
        getRemoteTopRatedMovies()
    }


    fun getRemotePopularMovies(){
        showLoading.value = true
        viewModelScope.launch {
            moviesRepository.getRemotePopularMovies(
                successCallback = {
                   // _movieList.postValue(it.results)
                    Response(true, null)
                },
                errorCallback = {
                    Response(false, it)

                }
            )
            showLoading.postValue(false)
        }
    }

    fun getRemoteUpcomingMovies(){
        viewModelScope.launch {
            moviesRepository.getRemoteUpcomingMovies(
                successCallback = {
                    Response(true, null)
                },
                errorCallback = {
                    Response(false, it)
                }
            )
        }
    }

    private fun getRemoteTopRatedMovies(){
        viewModelScope.launch {
            moviesRepository.getRemoteTopMovies(
                successCallback = {
                    Response(true, null)
                },
                errorCallback = {
                    Response(false, it)
                }
            )
        }
    }

    fun setMoviesOption(moviesOption: MOVIESSOPTION){
        _movieoption.value = moviesOption
    }

    fun onMovieClicked(movie: Movie) {
        _navigateToDetails.value = movie
    }

    fun onMovieCompleteNavigation(){
        _navigateToDetails.value = null
    }
    val movieList = Transformations.switchMap(_movieoption){
        showLoading.value = true
        when(it){
            MOVIESSOPTION.POPULAR -> getSavedPopularMovies()
            MOVIESSOPTION.UPCOMING -> getSavedUpcomingMovies()
            MOVIESSOPTION.TOPRATED -> getSavedTopMovies()
        }
    }

    fun getSavedPopularMovies(): LiveData<List<Movie>>{
        viewModelScope.launch {
            val movi= moviesRepository.getSavedPopularList()
            movies = Transformations.map(movi){
                Log.e("okkkPOP", it.size.toString())
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

    fun getSavedTopMovies(): LiveData<List<Movie>>{
        viewModelScope.launch {
            val movi= moviesRepository.getSavedTopList()
            movies = Transformations.map(movi){
                TopMoviesContainer(it).toMovie()
            }
        }
        return movies
    }

    enum class MOVIESSOPTION{
        POPULAR, UPCOMING, TOPRATED
    }

    enum class MovieAPIStatus {
        LOADING,
        ERROR,
        DONE
    }
}