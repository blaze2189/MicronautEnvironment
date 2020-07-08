package com.micronaut.docker.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

    {
        System.out.println("Bean "+this.getClass().getName());
    }

    public String getAirportCd() {
        return airportCd;
    }

    public void setAirportCd(String airportCd) {
        this.airportCd = airportCd;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCty() {
        return airportCty;
    }

    public void setAirportCty(String airportCty) {
        this.airportCty = airportCty;
    }

    @Id
    private String airportCd="";
    private String airportName;
    private String airportCty;

}
