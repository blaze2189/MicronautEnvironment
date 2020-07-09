package com.micronaut.docker.repository;

import com.micronaut.docker.entity.Airport;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {

    @Override
    List<Airport> findAll();
  //  Airport findByAirportCd(String airportCd);

    /*List<Airport> findAll();
    Airport findByAirportCd(String airportCD);*/

}
