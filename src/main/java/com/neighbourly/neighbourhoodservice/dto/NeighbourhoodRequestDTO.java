package com.neighbourly.neighbourhoodservice.dto;// DTO: NeighbourhoodRequestDTO.java

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NeighbourhoodRequestDTO {

    @NotBlank(message = "Neighbourhood name is mandatory")
    private String name;

    private String description;

    private String location;

    @NotNull(message = "Latitude is mandatory")
    @JsonProperty("latitude")

    private Double latitude;

    @NotNull(message = "Longitude is mandatory")
    @JsonProperty("longitude")

    private Double longitude;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}

