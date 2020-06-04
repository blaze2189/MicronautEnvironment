package com.micronaut.docker.service.impl;

import com.micronaut.docker.entity.Airport;
import com.micronaut.docker.exception.DataNotFoundException;
import com.micronaut.docker.repository.AirportRepository;
import com.micronaut.docker.service.IAirportService;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Singleton
public class AirportService implements IAirportService {


    {
        System.out.println("Bean " + this.getClass().getName());
    }

    @Inject
    private AirportRepository airportRepository;

    @Override
    public Airport addAirport(Airport airport){
        return airportRepository.save(airport);
    }

    @Override
    public List<String> addAirport(List<Airport> airportList) {
        airportRepository.saveAll(airportList);
        return  airportList.stream().map(a->a.getAirportCd()).collect(Collectors.toList());
    }

    @Override
    public void deleteAirport(String ariportCd) {
        Airport airport = airportRepository.findByAirportCd(ariportCd);
        isValidAirport(airport, ariportCd);
        airportRepository.delete(airport);
    }

    @Override
    public List<Airport> getAllAirports(){
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirportByCode(String airportCd) {
        Airport airport = airportRepository.findByAirportCd(airportCd);
        isValidAirport(airport, airportCd);
        return airport;
    }

    @Override
    public String updateAirport(Airport airport){
        return airportRepository.update(airport).getAirportCd();
    }

    private boolean isValidAirport(Airport airport, String airportCd) {
        if (airport != null) {
            return true;
        } else {
            throw new DataNotFoundException(airportCd);
        }
    }

}
