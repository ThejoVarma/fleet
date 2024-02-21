package com.fleet.countryapi;

import com.fleet.countryapi.dtos.CountrySuggestionDto;
import com.fleet.countryapi.entities.CountryEntity;
import com.fleet.countryapi.repositories.CountryRepository;
import com.fleet.countryapi.services.impl.CountrySuggestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CountrySuggestionServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountrySuggestionServiceImpl countrySuggestionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuggestCounties() {

        CountryEntity country1 = new CountryEntity();
        country1.setFips("01001");
        country1.setName("Autauga");
        country1.setState("AL");
        CountryEntity country2 = new CountryEntity();
        country2.setFips("01003");
        country2.setName("Baldwin");
        country2.setState("AL");
        List<CountryEntity> mockCountries = Arrays.asList(country1, country2);

        when(countryRepository.findByNameContainingOrStateContaining("cal", "cal"))
                .thenReturn(mockCountries);
        List<CountrySuggestionDto> suggestions = countrySuggestionService.suggestCounties("cal");

        assertEquals(2, suggestions.size());
        assertEquals("01001", suggestions.get(0).getFips());
        assertEquals("AL", suggestions.get(0).getState());
        assertEquals("Autauga", suggestions.get(0).getName());
        assertEquals("01003", suggestions.get(1).getFips());
        assertEquals("AL", suggestions.get(1).getState());
        assertEquals("Baldwin", suggestions.get(1).getName());
    }

    @Test
    public void testSuggestCounties_EmptyQuery() {
        List<CountrySuggestionDto> suggestions = countrySuggestionService.suggestCounties("");

        assertEquals(Collections.emptyList(), suggestions);
    }

}

