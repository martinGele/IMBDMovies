package com.martin.myapplication.data.remote.repository

import com.martin.myapplication.data.remote.api.MoviesApi
import com.martin.myapplication.data.remote.repository.model.TopRatedMoviesResult
import com.slack.eithernet.ApiResult
import com.slack.eithernet.ApiResult.Failure
import com.slack.eithernet.ApiResult.Failure.NetworkFailure
import com.slack.eithernet.ApiResult.Failure.UnknownFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class MoviesRepository @Inject constructor(private val moviesApi: MoviesApi) {

    suspend fun getTopMovies(): Flow<TopRatedMoviesResult> = flow {
        emit(TopRatedMoviesResult.Loading)
        val topRatedMoviesResult = when (val result = moviesApi.getTopMovies()) {
            is ApiResult.Success -> TopRatedMoviesResult.Success(result.value)
            is Failure -> when (result) {
                is NetworkFailure -> TopRatedMoviesResult.Error(result.error)
                is UnknownFailure -> TopRatedMoviesResult.Error(result.error)
                is Failure.ApiFailure -> TopRatedMoviesResult.ApiError(result.error?.statusMessage)
                is Failure.HttpFailure -> TopRatedMoviesResult.ApiError(result.error?.statusMessage)
            }
        }
        emit(topRatedMoviesResult)
    }
}