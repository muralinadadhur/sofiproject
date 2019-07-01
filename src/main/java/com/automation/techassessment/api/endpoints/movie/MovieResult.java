package com.automation.techassessment.api.endpoints.movie;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Some Author (some.author@addepar.com)
 */
@Getter
@Setter
public class MovieResult {
    private String vote_count;
    private Integer id;
    private Boolean video;
    private Double vote_average;
    private String title;
    private Double popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private String backdrop_path;
    private Boolean adult;
    private String overview;
    private String release_date;
}
