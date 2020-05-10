package com.ss21.ratingdataservice.resources;

import com.ss21.ratingdataservice.models.Rating;
import com.ss21.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingData")
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable(name="movieId") String movieId){
        return new Rating(movieId, 9);
    }

    /*@RequestMapping("users/{userId}")
    public List<Rating> getUserRating(@PathVariable(name="userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 5),
                new Rating("1235", 9)
        );
        return ratings;
    }*/

//    Alternative

    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable(name="userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 5),
                new Rating("1235", 9)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);

        return userRating;
    }
}
