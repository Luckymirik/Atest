package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.impl.FlightServiceImpl;
import com.gridnine.testing.util.FlightBuilder;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Все полеты");
        List<Flight> flights = FlightBuilder.createFlights();
        flights.forEach(System.out::println);
        System.out.println();

        System.out.println("Вылеты в текущий и после текущего момента времени");
        FlightService nowAndAfter = new FlightServiceImpl(flights);
        List<Flight> one = nowAndAfter.upToTheCurrentMomentInTime().save();
        one.forEach(System.out::println);
        System.out.println();

        System.out.println("Сегменты с датой прилёта позже даты вылета");
        FlightService arrivalLaterDeparture = new FlightServiceImpl(flights);
        List<Flight> two = arrivalLaterDeparture.segmentsWithAnArrivalDateEarlierThanTheDepartureDate().save();
        two.forEach(System.out::println);
        System.out.println();

        System.out.println("Перелеты где общее время проведённое на земле не превышает два часа");
        FlightService lessThanTwo = new FlightServiceImpl(flights);
        List<Flight> three = lessThanTwo.totalTimeSpentOnEarthExceedsTwoHours().save();
        three.forEach(System.out::println);
        System.out.println();

    }

}