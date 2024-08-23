package com.martin.myapplication.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martin.myapplication.data.remote.repository.MoviesRepository
import com.martin.myapplication.data.remote.repository.model.TopRatedMoviesResult
import com.martin.myapplication.presentation.state.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTopMovies().collect { result ->
                when (result) {
                    is TopRatedMoviesResult.Success -> {
                        _state.update { it.copy(movies = result.data.results, loading = false) }
                    }

                    is TopRatedMoviesResult.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = result.error.message,
                                loading = false
                            )
                        }
                    }

                    is TopRatedMoviesResult.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }
                }
            }
        }
    }
}