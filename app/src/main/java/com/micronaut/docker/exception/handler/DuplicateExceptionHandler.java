package com.micronaut.docker.exception.handler;

import com.micronaut.docker.entity.Airport;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.netty.NettyHttpRequest;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@Singleton
@Requires(classes = {PersistenceException.class, ExceptionHandler.class})
public class DuplicateExceptionHandler implements ExceptionHandler<PersistenceException, HttpResponse> {

    {
        System.out.println("Bean " + this.getClass().getName());
    }

    @Override
    public HttpResponse<Map<String,String>> handle(HttpRequest request, PersistenceException exception) {
        Map<String, String> responseBody = new HashMap<>();
        if (request instanceof NettyHttpRequest) {
           String cd = ((Airport) ((NettyHttpRequest) request).getBody(Airport.class).get()).getAirportCd();
           responseBody.put("airportCd",cd);
            responseBody.put("cause" ,String.format("%s already exists",cd));
        }
        return HttpResponse.badRequest(responseBody);
    }
}
