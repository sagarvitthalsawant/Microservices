package com.svs.models;

public class Movie {

    private String movieId;
    private String name;

    //To marshall something to an object which is not an object we need an empty constructor
    public Movie() {
    }


    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
