package com.example.testjdbc.controllers;

import java.util.List;

import com.example.testjdbc.entities.Property;
import com.example.testjdbc.entities.Review;
import com.example.testjdbc.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ApiController
 */
@RestController
@RequestMapping(path="/api")
public class PropertyController {
    
    @Autowired
    PropertyRepository propertyRepository;

    @GetMapping(path="/propertys", produces="application/json")
    public List<Property> displayPropertys(){
        return propertyRepository.getAllPropertys();
    }

    @GetMapping(path="/properties", produces="application/json")
    public List<Property> searchProperties(
        @RequestParam("address") String address,
        @RequestParam("numRooms") int numRooms,
        @RequestParam("price") int price
        ){
        return propertyRepository.searchProperties(address, numRooms, price);
    }
    @GetMapping(path="/bookedProperties", produces="application/json")
    public List<Property> displayBookedProperties(){
        return propertyRepository.getBookedProperties();
    }

    @GetMapping(path="/reviewedProperties", produces="application/json")
    public List<Property> displayReviewedProperties(){
        return propertyRepository.getReviewedProperties();
    }

    @GetMapping(path="/create_property", produces="application/json")
    public void createProperty(
        @RequestParam("address") String address,
        @RequestParam("numRooms") int numRooms,
        @RequestParam("price") int price,
        @RequestParam("allowSmoking") boolean allowSmoking,
        @RequestParam("maxGuestNum") int maxGuestNum
    ){
        Property property = new Property();
        property.setAddress(address);
        property.setAllowSmoking(allowSmoking);
        property.setMaxGuestNum(maxGuestNum);
        property.setNumRooms(numRooms);
        property.setPrice(price);
        
        propertyRepository.createProperty(property);
    }

    @PostMapping(value="/properties", produces="application/json")
    public Property createProperty(@RequestBody Property property){
        propertyRepository.createProperty(property);
        return property;
    }

    @PostMapping(value ="/properties/{id}/reviews", produces = "application/json")
    public Review createReviewfromPropertyController(@RequestBody Review review, @PathVariable("id") int id) {
        propertyRepository.createReviewForProperty(review, id);
        return review;
    }
}