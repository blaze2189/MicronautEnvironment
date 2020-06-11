package com.micronaut.docker.service.reactive;

import com.micronaut.docker.entity.Airport;
import io.reactivex.Maybe;

import java.util.List;
import java.util.Map;

public interface IReactiveAirportService {

    Maybe<List<Airport>> getAllAirports();

    Maybe <Airport> getAirportByCd(String airportCd);

    void deleteAiport(String airportCd);

    Maybe<Airport> saveAirport(Airport airport);

    Maybe<Map<String,String>> saveAirports(List<Airport> airportList);

}
