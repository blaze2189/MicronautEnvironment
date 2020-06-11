package com.micronaut.docker.controller.reactive;

import com.micronaut.docker.entity.Airport;
import com.micronaut.docker.service.reactive.IReactiveAirportService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.reactivex.Maybe;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller("/airport/reactive/")
public class ReactiveAirportController {


    @Inject
    private IReactiveAirportService reactiveAirportService;

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe<List<Airport>>> getAllCities() {
        return HttpResponse.ok(reactiveAirportService.getAllAirports());
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe<Airport>> save(@Body Airport airport) {
        return HttpResponse.ok(reactiveAirportService.saveAirport(airport));
    }

    @Post("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe<Map<String,String>>>netasave(@Body List<Airport> airportList){
        return HttpResponse.ok(reactiveAirportService.saveAirports(airportList));
    }

    @Put(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Maybe> update(@Body Airport airport) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Delete("/{airportCD}")
    public void delete(String airportCD) {
        reactiveAirportService.deleteAiport(airportCD);
    }

    @Get("/{airportCd}")
    public HttpResponse<Maybe<Airport>> getByCd(String airportCd) {
        return HttpResponse.ok(reactiveAirportService.getAirportByCd(airportCd));
    }

}
