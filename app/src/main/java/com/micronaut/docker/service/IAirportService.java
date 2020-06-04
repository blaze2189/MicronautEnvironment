package com.micronaut.docker.service;

import com.micronaut.docker.entity.Airport;
import io.reactivex.Single;

import java.util.List;

public interface IAirportService {

    Airport addAirport(Airport airport);

    List<String> addAirport(List<Airport> airport);

    void deleteAirport(String ariportCd);

    List<Airport> getAllAirports();

    Airport getAirportByCode(String airportCd);

    String updateAirport(Airport airport);
}
