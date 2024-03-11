package com.gridnine.testing.service.impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.service.FlightService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FlightServiceImpl implements FlightService {


    private final List<Flight> flights;
    public FlightServiceImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }
    @Override
    public FlightService upToTheCurrentMomentInTime() {
        flights.removeIf(f ->
                f.getSegments()
                        .stream()
                        .anyMatch(s -> s
                                .getDepartureDate()
                                .isBefore(LocalDateTime.now())));
        return this;
    }
    @Override
    public FlightService segmentsWithAnArrivalDateEarlierThanTheDepartureDate(){
         flights.removeIf(f ->
                f.getSegments()
                        .stream()
                        .anyMatch(s -> s
                                .getArrivalDate().isBefore(s.getDepartureDate())));
         return this;

    }
    @Override
    public FlightService totalTimeSpentOnEarthExceedsTwoHours(){
        flights.removeIf(f ->
                        IntStream.
                range(1,f.getSegments().size())
                        .mapToObj(i->
                                Duration.between(f.getSegments().get(i-1).getArrivalDate(),f.getSegments()
                                        .get(i).getDepartureDate()).abs())
                        .reduce(Duration.ZERO,Duration::plus)
                        .toHours()>=2);
        return this;

    }

    @Override
    public List<Flight> save() {
        return flights;
    }
}
