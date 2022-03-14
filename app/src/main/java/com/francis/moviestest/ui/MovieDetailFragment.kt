package com.francis.moviestest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.francis.moviestest.databinding.MovieDetailFragmentBinding
import com.francis.moviestest.model.Movie
import com.francis.moviestest.utility.loadPicture

class MovieDetailFragment : Fragment() {

    lateinit var binding: MovieDetailFragmentBinding
    lateinit var movie: Movie
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(inflater)
        movie = MovieDetailFragmentArgs.fromBundle(arguments!!).selectedMovie
        //setupView(movie)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view, movie)
    }

    private fun setupView(view: View, movie: Movie){
        binding.movieName.text = movie.title
        binding.plot.text = movie.overview
        binding.releaseDate.text = movie.release_date
        binding.vote.text = movie.vote_average.toString()
        loadPicture(view, movie.poster_path, binding.movieImage)
    }

}