package com.micronaut.docker.controller;

import com.micronaut.docker.entity.Airport;
import com.micronaut.docker.repository.AirportRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller("/airport")
public class AirportController {

    @Inject
    private AirportRepository airportRepository;

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<Airport> getAllCities() {
        return airportRepository.findAll();
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String save(@Body Airport airport) {
        try {
            airportRepository.save(airport);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return airport.getAirportCd();
    }

    @Put(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public String update(@Body Airport airport) {
        try {
            airportRepository.update(airport);
        } catch (Exception e) {
            return e.getMessage();
        }
        return airport.getAirportCd();
    }

    @Delete("/{airportCD}")
    public void delete(String airportCD) {
        try {
            Airport airport = airportRepository.findByAirportCd(airportCD);
            System.out.println(airport);
            airportRepository.delete(airport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Get("/{airportCd}")
    public Airport getByCd(String airportCd) {
        try {
            return airportRepository.findByAirportCd(airportCd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
