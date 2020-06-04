package com.micronaut.docker.service;

import com.micronaut.docker.entity.Airport;

import java.util.List;

public interface IAirportService {
    Airport addAirport(Airport airport);

    void deleteAirport(String ariportCd);

    List<Airport> getAllAirports();

    Airport getAirportByCode(String airportCd);

    String updateAirport(Airport airport);
}
