package com.micronaut.docker.exception.handler;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.micronaut.docker.entity.Airport;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.server.netty.NettyHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Singleton
@Requires(classes = {PersistenceException.class, ExceptionHandler.class})
public class DuplicateExceptionHandler implements ExceptionHandler<PersistenceException, HttpResponse> {

    Logger logger = LoggerFactory.getLogger(DuplicateExceptionHandler.class);

    {
        System.out.println("Bean " + this.getClass().getName());
    }

    @Override
    public HttpResponse<Map<String, String>> handle(HttpRequest request, PersistenceException exception) {
        logger.info("Catched exception {}", exception);
        Map<String, String> responseBody = new HashMap<>();
        if (request instanceof NettyHttpRequest) {
            if (isType(Airport.class,request)) {
                String cd = ((Airport)request.getBody(Airport.class).get()).getAirportCd();
                responseBody.put("airportCd", cd);
                responseBody.put("cause", String.format("%s already exists", cd));
            } else if (isType(List.class,request)) {
                responseBody.put("error:",exception.getCause().getCause().toString());
            }
        }
        return HttpResponse.badRequest(responseBody);
    }

    private boolean isType(Class clazz, HttpRequest request){
        try{
            request.getBody(clazz).get();
            return true;
        }catch (Exception e){
            logger.error("error exception {}",e.getClass());
            return false;
        }
    }

}