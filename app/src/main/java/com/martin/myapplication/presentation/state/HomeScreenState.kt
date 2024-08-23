package com.martin.myapplication.presentation.state


import androidx.compose.runtime.Stable
import com.martin.myapplication.data.remote.model.TopRatedMovies

@Stable
data class HomeScreenState(
    var title: String = "",
    var movies: List<TopRatedMovies.Result> = emptyList(),
    var errorMessage: String? = null,
    var loading: Boolean = false
)