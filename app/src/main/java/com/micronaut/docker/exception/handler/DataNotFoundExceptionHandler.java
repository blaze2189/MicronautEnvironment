package com.micronaut.docker.exception.handler;

import com.micronaut.docker.exception.DataNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Requires(classes = {PersistenceException.class,ExceptionHandler.class})
public class DataNotFoundExceptionHandler implements ExceptionHandler<DataNotFoundException, HttpResponse> {


    @Override
    public HttpResponse<Map<String,String>> handle(HttpRequest request, DataNotFoundException exception){
        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("dataNotFound",exception.getMessage());
        return HttpResponse.badRequest(responseBody);
    }

}
