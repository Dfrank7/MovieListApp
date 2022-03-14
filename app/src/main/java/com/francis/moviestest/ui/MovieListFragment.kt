package com.francis.moviestest.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.francis.moviestest.R
import com.francis.moviestest.data.domain.PopularMoviesContainer
import com.francis.moviestest.data.domain.toMovie
import com.francis.moviestest.databinding.MoviesListFragmentBinding
import com.francis.moviestest.ui.adapter.MovieListAdapter
import com.francis.moviestest.ui.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment: Fragment(){
    private lateinit var binding: MoviesListFragmentBinding

    private lateinit var moviesAdapter: MovieListAdapter
    private val moviesViewModel by viewModel<MoviesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesListFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupObservers(){
        moviesViewModel.run {
            movieList.observe(viewLifecycleOwner){movies->
                moviesAdapter.submitList(movies)
            }

            status.observe(viewLifecycleOwner){
                it.let {
                    when(it){
                        MoviesViewModel.MovieAPIStatus.LOADING -> binding.statusLoadingWheel.visibility = View.VISIBLE
                        MoviesViewModel.MovieAPIStatus.DONE-> binding.statusLoadingWheel.visibility = View.GONE
                        MoviesViewModel.MovieAPIStatus.ERROR->{
                            binding.statusLoadingWheel.visibility = View.GONE
                        }
                    }
                }
            }

            navigateToDetails.observe(viewLifecycleOwner){
                it.let {
                    this@MovieListFragment.findNavController().
                    navigate(MovieListFragmentDirections.actionShowDetail(it!!))
                    moviesViewModel.onMovieCompleteNavigation()
                }
            }
        }
    }

    private fun setupView(){
        moviesAdapter = MovieListAdapter(MovieListAdapter.MovieListener{
            moviesViewModel.onMovieClicked(it)
        })
        binding.run {
            binding.movieRecycler.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = moviesAdapter
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.movies_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_popular_menu-> moviesViewModel.setMoviesOption(MoviesViewModel.MOVIESSOPTION.POPULAR)
            R.id.show_upcoming_menu -> moviesViewModel.setMoviesOption(MoviesViewModel.MOVIESSOPTION.UPCOMING)
        }
        return true
    }

}