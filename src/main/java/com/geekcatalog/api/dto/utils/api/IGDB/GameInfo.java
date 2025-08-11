package com.geekcatalog.api.dto.utils.api.IGDB;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GameInfo(String name, @JsonProperty("release_dates") List<Long> releaseDates, List<Integer> genres, @JsonProperty("involved_companies") List<Integer> involvedCompanies, List<Integer> platforms, long cover) {

}