package com.fleet.countryapi.services.impl;

import com.fleet.countryapi.dtos.CountrySuggestionDto;
import com.fleet.countryapi.entities.CountryEntity;
import com.fleet.countryapi.repositories.CountryRepository;
import com.fleet.countryapi.services.CountrySuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountrySuggestionServiceImpl implements CountrySuggestionService {

   @Autowired
   CountryRepository countryRepository;

    @Override
    public List<CountrySuggestionDto> suggestCounties(String q) {
        if (q == null || q.isEmpty()) {
            return Collections.emptyList();
        }
        String name;
        String state;
        if (q.contains(",")) {
            String[] fragments = q.split(",");
            name = fragments[0].trim();
            state = fragments.length > 1 ? fragments[1].trim() : "";
        } else {
            name = q;
            state = q;
        }

        return Optional.ofNullable(countryRepository
                .findByNameContainingOrStateContaining(name,state))
                .orElse(Collections.emptyList())
                .stream()
                .limit(5)
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CountrySuggestionDto mapToDto(CountryEntity country) {
        return new CountrySuggestionDto(country.getFips(), country.getState(), country.getName());
    }
}
