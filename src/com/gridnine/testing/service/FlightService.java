package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightService {
    FlightService upToTheCurrentMomentInTime();

    FlightService segmentsWithAnArrivalDateEarlierThanTheDepartureDate();

    FlightService totalTimeSpentOnEarthExceedsTwoHours();

    List<Flight> save();
}
