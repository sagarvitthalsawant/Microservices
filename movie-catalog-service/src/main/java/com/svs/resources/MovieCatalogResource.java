package com.svs.resources;

import com.svs.models.CatalogItem;
import com.svs.models.Movie;;
import com.svs.models.Rating;
import com.svs.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

//        RestTemplate restTemplate = new RestTemplate();
//        Movie movie  = restTemplate.getForObject("http://localhost:8081/movies/123", Movie.class);

//        WebClient.Builder builder = new WebClient.builder();

        //get all rated movies id
        /*List<Rating> ratings = Arrays.asList(
                new Rating("1234", 5),
                new Rating("1235", 9)
        );
        */
//        alternative 1
//        List<Rating> ratings = restTemplate.getForObject("http://localhost:8082/ratingData/users",
//                ParameterizedTypeReference<List<Rating>>);
//        alternative 2

        UserRating userRating = restTemplate.getForObject("http://localhost:8082/ratingData/users/" + userId, UserRating.class);

        return userRating.getUserRating().stream().map(rating -> {
            //for each movie id call info-service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
                            /*new CatalogItem("Titanic", "Jack and Rose", 9)*/
            //put them all together
            return new CatalogItem(movie.getMovieId(), "Jack and Rose", rating.getRating()); // calls to 2 API
        }).collect(Collectors.toList());

//        return Collections.singletonList(new CatalogItem("Titanic", "Jack and Rose", 9));

        //Alternative to Rest Template is Web Client
        /*Movie movie = webClientBuilder.build()
                    .post() // for post calls
                    .get() //for get calls
                    .uri("http://localhost:8081/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class) // asynch promise || whatever you get back as response convert it into movie class
                    .block(); remove this to make calls asynchronous*/
    }
}
