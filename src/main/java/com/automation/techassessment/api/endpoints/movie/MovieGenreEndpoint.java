package com.automation.techassessment.api.endpoints.movie;



import com.automation.techassessment.api.model.GenreList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Creating an interface with the Genre end point details.
 * Refer to function in the util class that is populating the list of the genres to the Genre object.
 * This interface is created within the movie package because this genre list is related to movies.
 */

public interface MovieGenreEndpoint {
    String BASE_URL = "genre";

    @GET(BASE_URL + "/movie/list")
    Call<GenreList> getGenreList();
}
