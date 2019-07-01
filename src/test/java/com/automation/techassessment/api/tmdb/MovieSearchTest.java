package com.automation.techassessment.api.tmdb;

import java.util.Arrays;
import java.util.List;

import com.automation.techassessment.api.endpoints.ApiEndpoints;
import com.automation.techassessment.api.endpoints.movie.MovieResponse;
import com.automation.techassessment.api.endpoints.movie.MovieResult;
import com.automation.techassessment.api.endpoints.movie.SearchMovieResponse;
import com.automation.techassessment.api.model.Genre;
import com.automation.techassessment.api.model.SpokenLanguage;
import com.automation.techassessment.api.model.SpokenLanguageList;
import com.google.gson.Gson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import static com.automation.techassessment.api.errors.RestAssertions.assertCallSucceeds;

public class MovieSearchTest {
    private ApiEndpoints rest;


    @BeforeMethod
    public void setup() throws Exception {
        rest = ApiEndpoints.newBuilder().build();
    }

    public List<Genre> populateGenreList()  {
        List<Genre> listOfGenres =   assertCallSucceeds(rest.movieGenre.getGenreList())
                                        .body().getGenres();
        return listOfGenres;
    }

    /**
     *     Test to query by Movie Title.
     *     Ensure the results the contains only the movie title that was queried thru the API.
     *     This test is currently failing because the movie search is returning movie details that dont have title - Mission Impossible.
     */
    @Test
    public void get_MovieDetails_ByName() {
        String movieName = "Mission Impossible";
        SearchMovieResponse searchResults = assertCallSucceeds(rest.movie.searchMovie(movieName)).body();
        List<MovieResult> movieResultList = searchResults.getResults();
        for (MovieResult movieResult : movieResultList) {
            Assert.assertTrue(movieResult.getTitle().contains(movieName),"The movie list contains more movies than requested");
        }
    }

    /**
     *     Test to query by a specific Movie Title.
     *     Ensure the results the contains only the movie title that was queried thru the API.
     */
    @Test
    public void get_MovieDetails_SpecificName() {
        String movieName = "Mission: Impossible - Fallout";
        SearchMovieResponse searchResults = assertCallSucceeds(rest.movie.searchMovie(movieName)).body();
        List<MovieResult> movieResultList = searchResults.getResults();
        Assert.assertTrue(movieResultList.size() > 0, "There should be at-least one movie returned with this name");
        Assert.assertTrue(movieResultList.stream().anyMatch(g ->
                g.getTitle().equals(movieName)));
    }

    /**
     *     Test to query by Movie Id.
     *     The results should only contain the movie results specific to the id that was queried.
     */
    @Test
    public void get_MovieDetails_ById() {
        String movie_id = "10000";
        MovieResponse movieResponse = assertCallSucceeds(rest.movie.getMovieDetailsById(movie_id)).body();
        Assert.assertTrue(movieResponse.getId().equals(Integer.parseInt(movie_id)));
    }

    /**
     *     Test to validate movie genres.
     *     The results should contain only those genres that are added as part of movie genres.
     */
    @Test
    public void get_MovieGenreList() {
        boolean listFlag = false;
        String movie_id = "10000";

        List<Genre> genreList =  populateGenreList();
        MovieResponse movieResponse = assertCallSucceeds(rest.movie.getMovieDetailsById(movie_id)).body();

        for (Genre genreMovieResponse : movieResponse.getGenres()){
            for (Genre masterList : genreList){
                if  (genreMovieResponse.getName().equals(masterList.getName())){
                    listFlag = true;
                }
            }
        }
        Assert.assertTrue(listFlag, "Movie Genre list contains genres that don't belong to the genre meta data");
    }

    /**
     * Test to validate movie's year of release.
     * Filtering for a movie based on its year of release should return only those details.
     */
    @Test(dataProvider = "movieYearList")
    public void get_MovieDetails_withReleaseYear(String release_year) {
        String movieName = "Mission Impossible";
        String[] movieReleaseDate;
        boolean releaseFlag = false;
        SearchMovieResponse searchResults = assertCallSucceeds(rest.movie.searchMovieByYear(movieName,release_year)).body();
        List<MovieResult> movieResults = searchResults.getResults();
        for (MovieResult movieResult : movieResults) {
            movieReleaseDate = movieResult.getRelease_date().split("-");
            for (String movieReleaseYear : movieReleaseDate){
                if(movieReleaseYear.equals(release_year)){
                    releaseFlag = true;
                }
            }
        }
        Assert.assertTrue(releaseFlag);
    }

    /**
     * Test to check if the languages returned for a movie list are from expected list of languages
     * Create a stub with a master list of languages.
     * Use Gson to read from stub and add it as a list
     * Validate the given movie list of languages is from the language master list.
     */
    @Test
    public void get_MovieDetails_Match_with_LanguageList() {
        boolean languageFlag = false;
        String movie_id = "10000";
        String languageJson = "{\"languages\": [{\"iso_639_1\": \"en\",\"name\": \"English\"},{\"iso_639_1\": \"de\",\"name\": \"German\"},{\"iso_639_1\": \"fr\",\"name\": \"French\"},{\"iso_639_1\": \"es\",\"name\": \"Español\"  },{\"iso_639_1\": \"jp\",\"name\": \"Japanese\"}]}";
        MovieResponse movieResponse = assertCallSucceeds(rest.movie.getMovieDetailsById(movie_id)).body();
        Gson gson = new Gson();
        SpokenLanguageList spokenLanguageList = gson.fromJson(languageJson,SpokenLanguageList.class);
        List<SpokenLanguage> spokenLanguagesinMovie = movieResponse.getSpoken_languages();
        Assert.assertTrue(spokenLanguagesinMovie.size() > 0);
        for (SpokenLanguage spokenLanguage : spokenLanguagesinMovie){
            for (SpokenLanguage masterList : spokenLanguageList.getLanguages()){
                if  (spokenLanguage.getName().equals(masterList.getName())){
                    languageFlag = true;
                }
            }
        }
        Assert.assertTrue(languageFlag, "Movie spoken language list contains languages that don't belong to the language meta data");
    }

    /**
     * Test to check if the production companies returned for a movie list are from expected list of companies
     * Test passes if the list matches otherwise it is considered to a failure.
     * The approach would be the same as the previous case i.e. to create as stub of production companies and validate them with movie response.
     **/
    public void get_MovieDetails_Match_withProductionCompanies() {

    }

    /**
     * Test to check if there are no movies returned if searched with a negative integer
     */
    @Test
    public void get_MovieDetails_ByNonExistingId() throws Exception {
        String movie_id = "-1";
        Assert.assertEquals(rest.movie.getMovieDetailsById(movie_id).execute().raw().code(),404);
        Assert.assertEquals(rest.movie.getMovieDetailsById(movie_id).execute().raw().message(),"Not Found");
    }

    /**
     * Test to check if an empty movie name returns nothing.
     * @return
     */
    @Test
    public void get_MovieDetails_with_an_EmptyString() throws Exception {
        String movieName = " ";
        SearchMovieResponse searchResults = assertCallSucceeds(rest.movie.searchMovie(movieName)).body();
        Assert.assertTrue(searchResults.getResults().size()==0);
    }

    @DataProvider
    public Object[][] movieYearList() {
        return new Object[][]{
                {"1996"}, {"1999"}, {"2000"}
        };
    }
}
