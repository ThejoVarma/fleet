package com.fleet.countryapi.services;

import com.fleet.countryapi.dtos.CountrySuggestionDto;
import com.fleet.countryapi.entities.CountryEntity;

import java.util.List;

public interface CountrySuggestionService {

    List<CountrySuggestionDto> suggestCounties(String q);

}
