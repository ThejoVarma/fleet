package com.fleet.countryapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class CountryEntity {

    @Id
    @Column
    private String fips;

    @Column
    private String state;

    @Column
    private String name;
}
