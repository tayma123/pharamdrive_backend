package com.pharamdrive.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OSMGeocodingResponse {

    private double lat;
    private double lon;

    // Getters and setters
}