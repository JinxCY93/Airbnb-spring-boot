package com.example.testjdbc.controllers;

import java.util.List;

import com.example.testjdbc.entities.Payment;
import com.example.testjdbc.repositories.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@RequestMapping(path="/api")
public class PaymentController {
    
    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping(path="/payments", produces="application/json")
    public List<Payment> displayPayments(){
        return paymentRepository.getAllPayments();
    }

    @GetMapping(path="/bookings/{id}/payments", produces="application/json")
    public List<Payment> displayPaymentsForBooking(@PathVariable("id") int id){
        return paymentRepository.getPaymentsForBooking(id);
    }
}