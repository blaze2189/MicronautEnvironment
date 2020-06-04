package com.micronaut.docker.controller;

import com.micronaut.docker.entity.Airport;
import com.micronaut.docker.service.IAirportService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import javax.inject.Inject;
import java.util.List;

@Controller("/airport")
public class AirportController {

    @Inject
    private IAirportService airportService;

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List> getAllCities() {
        return HttpResponse.ok(airportService.getAllAirports());
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Airport> save(@Body Airport airport) {
        airportService.addAirport(airport);
        return HttpResponse.ok(airport);
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces=MediaType.APPLICATION_JSON)
    public HttpResponse<List> save(@Body List<Airport> airportList){
        throw new RuntimeException("Not implemented yet");
    }

    @Put(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<String> update(@Body Airport airport) {
        String airportCd = airportService.updateAirport(airport);
        return HttpResponse.ok(airportCd);
    }

    @Delete("/{airportCD}")
    public void delete(String airportCD) {
        airportService.deleteAirport(airportCD);
    }

    @Get("/{airportCd}")
    public HttpResponse<Airport> getByCd(String airportCd) {
        Airport airport = airportService.getAirportByCode(airportCd);
        return HttpResponse.ok(airport);
    }


}
