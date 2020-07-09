package com.micronaut.docker.exception;


import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.persistence.PersistenceException;

import javax.inject.Singleton;

import io.micronaut.http.HttpResponse;
import lombok.NoArgsConstructor;

@Singleton
@Requires(classes = { ExceptionHandler.class})
public class Handler {



}
