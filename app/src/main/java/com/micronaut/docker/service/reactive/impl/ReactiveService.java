package com.micronaut.docker.service.reactive.impl;

import com.micronaut.docker.entity.Airport;
import com.micronaut.docker.exception.handler.DuplicateExceptionHandler;
import com.micronaut.docker.repository.AirportRepository;
import com.micronaut.docker.service.reactive.IReactiveAirportService;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReactiveService implements IReactiveAirportService {

    Logger logger = LoggerFactory.getLogger(ReactiveService.class);

    @Inject
    private AirportRepository airportRepository;

    @Override
    public Maybe<List<Airport>> getAllAirports() {
        return Maybe.just("").subscribeOn(Schedulers.io()).
                map(t -> airportRepository.findAll());
    }

    @Override
    public Maybe<Airport> getAirportByCd(String airportCd) {
        return Maybe.just(airportCd).subscribeOn(Schedulers.io()).
                map(t -> airportRepository.findById(airportCd).get());
    }

    @Override
    public void deleteAiport(String airportCd) {
        Maybe.just(airportCd).subscribeOn(Schedulers.io()).
                map(cd -> {
                    Airport airport = airportRepository.findById(airportCd).get();
                    airportRepository.delete(airport);
                    return null;
                });
    }

    @Override
    public Maybe<Airport> saveAirport(Airport airport) {
        return Maybe.just(airport).subscribeOn(Schedulers.io()).
                map(a -> airportRepository.save(a));
    }

    @Override
    public Maybe<Map<String, String>> saveAirports(List<Airport> airportList) {
        return Maybe.just(airportList).subscribeOn(Schedulers.io()).
                map(list -> asyncSave(list));
    }

    private Map<String, String> asyncSave(List<Airport> listAirpot) {
        Map<String, String> result = new HashMap<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            simulateLatency();
            try {
                airportRepository.saveAll(listAirpot);
            }catch (Exception e){
                logger.error("Error while trying to add airports {}",e);
                throw e;
            }
             return 1;
        });
        result.put("status","doing");
        return result;
    }

    private void simulateLatency(){
        try {
            logger.debug("debug: simulate latency");
            logger.info("info: simulate latency");
            logger.error("error: simulate latency");
            Thread.sleep(10000);
            logger.info("info finished latency");
        } catch (InterruptedException e) {
            logger.error("---{}",e.getMessage());
        }
    }

}
