package com.fleet.countryapi.controllers;

import com.fleet.countryapi.dtos.CountrySuggestionDto;
import com.fleet.countryapi.services.CountrySuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggest")
public class CountryController {

    @Autowired
    private CountrySuggestionService countySuggestionService;

    @GetMapping
    public ResponseEntity<List<CountrySuggestionDto>> suggestCounties(@RequestParam String q) {
        List<CountrySuggestionDto> countrySuggestionDtoList = countySuggestionService.suggestCounties(q);
        return new ResponseEntity<>(countrySuggestionDtoList, HttpStatus.OK);
    }
}
