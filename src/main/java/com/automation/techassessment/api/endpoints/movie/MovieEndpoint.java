package com.automation.techassessment.api.endpoints.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieEndpoint {
    String BASE_URL = "movie";

    @GET("search/" + BASE_URL)
    Call<SearchMovieResponse> searchMovie(@Query("query") String query);

    @GET("search/" + BASE_URL)
    Call<SearchMovieResponse> searchMovieByYear(@Query("query") String query, @Query("year") String year);

    @GET(BASE_URL + "/{movieId}")
    Call<MovieResponse> getMovieDetailsById(@Path("movieId") String movieId);

}

