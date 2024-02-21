package com.fleet.countryapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CountrySuggestionDto {

    private String fips;

    private String state;

    private String name;
}
