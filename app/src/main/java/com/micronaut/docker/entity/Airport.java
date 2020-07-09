package com.micronaut.docker.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

    {
        System.out.println("Bean "+this.getClass().getName());
    }

    @Id
    private String airportCd ="";
    private String airportName;
    private String airportCty;

}
