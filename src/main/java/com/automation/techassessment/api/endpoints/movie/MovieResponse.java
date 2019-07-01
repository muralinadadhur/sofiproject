package com.automation.techassessment.api.endpoints.movie;

/**
 * @author Some Author (some.author@addepar.com)
 */
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.automation.techassessment.api.model.ProductionCountry;
import com.automation.techassessment.api.model.SpokenLanguage;
import com.automation.techassessment.api.model.Genre;
import com.automation.techassessment.api.model.ProductionCompany;

@Getter
@Setter
public class MovieResponse {
    private Boolean adult;
    private String backdrop_path;
    private String belongs_to_collection;
    private Integer budget;
    private List<Genre> genres = null;
    private String homepage;
    private Integer id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String poster_path;
    private List<ProductionCompany> production_companies = null;
    private List<ProductionCountry> production_countries = null;
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    private List<SpokenLanguage> spoken_languages = null;
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Double vote_average;
    private Integer vote_count;
    private Integer status_code;
    private String status_message;
}
