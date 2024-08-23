package com.martin.myapplication.data.remote.repository.model

import com.martin.myapplication.data.remote.model.TopRatedMovies

sealed class TopRatedMoviesResult {
    object Loading : TopRatedMoviesResult()
    data class Success(val data: TopRatedMovies) : TopRatedMoviesResult()
    data class Error(val error: Throwable) : TopRatedMoviesResult()
}
