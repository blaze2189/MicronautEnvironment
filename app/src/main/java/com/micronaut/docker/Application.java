package com.micronaut.docker;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.runtime.Micronaut;

@ConfigurationProperties("application")
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}