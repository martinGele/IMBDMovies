package com.martin.myapplication.data.remote.api

import com.martin.myapplication.data.remote.model.GeneralError
import com.martin.myapplication.data.remote.model.MovieDetails
import com.martin.myapplication.data.remote.model.TopRatedMovies
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApi {

    @GET("/3/movie/top_rated")
    suspend fun getTopMovies(
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ): ApiResult<TopRatedMovies, GeneralError>

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int,
    ): ApiResult<MovieDetails, GeneralError>
}