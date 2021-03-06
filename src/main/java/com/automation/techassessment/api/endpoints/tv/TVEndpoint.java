package com.automation.techassessment.api.endpoints.tv;

import com.automation.techassessment.api.endpoints.movie.SearchMovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TVEndpoint {
    String BASE_URL = "tv";

    @GET(BASE_URL + "/{tvId}")
    Call<TVResponse> getTVShowById(@Path("tvId") String tvId);

    @GET("search/" + BASE_URL)
    Call<SearchResponseTV> searchTV(@Query("query") String query);

    @GET("search/movie")
    Call<SearchMovieResponse> searchMovie(@Query("query") String query);

}