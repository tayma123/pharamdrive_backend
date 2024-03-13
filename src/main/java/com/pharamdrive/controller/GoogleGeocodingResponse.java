package com.pharamdrive.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GoogleGeocodingResponse {

    private String status;
    private List<Result> results;

    // Getters and setters

    // Inner class for results
    @Data
    public static class Result {
        private Geometry geometry;

        // Getter and setters
    }

    // Inner class for geometry
    @Data
    public static class Geometry {
        private Location location;

        // Getter and setters
    }

    // Inner class for location
    @Data
    public static class Location {
        private double lat;
        private double lng;

        // Getters and setters
    }
}
