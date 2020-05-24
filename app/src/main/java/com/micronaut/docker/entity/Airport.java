package com.micronaut.docker.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

    @Id
    private String airportCd="";
    private String airportName;
    private String airportCty;

}
