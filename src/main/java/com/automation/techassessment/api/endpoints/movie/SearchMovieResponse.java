package com.automation.techassessment.api.endpoints.movie;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

import com.automation.techassessment.api.model.TMDbObject;

@Getter
@Setter
public class SearchMovieResponse implements TMDbObject {
    private int page;
    private int total_results;
    private int total_pages;
    private List<MovieResult> results = null;
}
