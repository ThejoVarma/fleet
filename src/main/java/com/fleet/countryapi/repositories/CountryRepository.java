package com.fleet.countryapi.repositories;

import com.fleet.countryapi.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountryRepository extends JpaRepository<CountryEntity,String> {

    List<CountryEntity> findByNameContainingOrStateContaining(String name, String state);
}
