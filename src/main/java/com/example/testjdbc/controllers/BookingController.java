package com.example.testjdbc.controllers;

import java.util.List;

import com.example.testjdbc.entities.Booking;
import com.example.testjdbc.repositories.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * ApiController
 */
@RestController
@RequestMapping(path="/api")
public class BookingController {
    
    @Autowired
    BookingRepository bookingRepository;

    @GetMapping(path="/bookings", produces="application/json")
    public List<Booking> displayBookings(){
        return bookingRepository.getAllBookings();
    }
    
    @GetMapping(path="/properties/{id}/bookings", produces="application/json")
    public List<Booking> displayBookingsByPropertyId(@PathVariable("id") int id){
        return bookingRepository.getBookingsByPropertyId(id);
    }

    //get payments for booking with ID - something wrong with this cause the spring boot not able to run
    // @GetMapping(value="/bookings/{id}/payments",  produces = "application/json")
    // public List<Booking> getPaymentBookingID(@PathVariable("id") int id) {
    //     return bookingRepository.getPaymentByBookingId(id);
    // }

    @PostMapping(value="/bookings/{id}", produces = "application/json")
    public Booking updateTotalPrice(@RequestBody Booking booking, @PathVariable("id") int id) {
        bookingRepository.updateTotalPrice(booking, id);
        return booking;
    }
}