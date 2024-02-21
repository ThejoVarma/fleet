package com.fleet.countryapi.helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.countryapi.entities.CountryEntity;
import com.fleet.countryapi.repositories.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DataLoader {

    private final CountryRepository countryRepository;

    @Autowired
    public DataLoader(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void loadData() throws IOException {
        try (InputStream inputStream = new ClassPathResource("data.json").getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<CountryEntity> countries = objectMapper.readValue(inputStream, new TypeReference<List<CountryEntity>>() {
            });
            countryRepository.saveAll(countries);
        }
    }
}
