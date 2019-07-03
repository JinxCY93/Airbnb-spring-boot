package com.example.testjdbc.controllers;

import java.util.List;

import com.example.testjdbc.entities.Review;
import com.example.testjdbc.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * ApiController
 */
@RestController
@RequestMapping(path="/api")
public class ReviewController {
    
    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping(path="/reviews", produces="application/json")
    public List<Review> displayReviews(){
        return reviewRepository.getAllReviews();
    }

    @GetMapping(path="/properties/{id}/reviews", produces="application/json")
    public List<Review> displayReviewsByPropertyId(@PathVariable("id") int id){
        return reviewRepository.getReviewsByPropertyId(id);
    }

    @PostMapping(value="/properties/{id}/reviews", produces = "application/json")
    public Review createReview(@RequestBody Review review, @PathVariable("id") int id) {
        reviewRepository.createReview(review, id);
        return review;
    }
    
}