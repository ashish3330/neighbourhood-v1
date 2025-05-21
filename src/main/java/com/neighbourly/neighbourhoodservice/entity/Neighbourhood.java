package com.neighbourly.neighbourhoodservice.entity;// Entity: Neighbourhood.java

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "neighbourhood")
public class Neighbourhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long neighborhoodId;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String location;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    // Getters and Setters
    public Long getNeighbourhoodId() { return neighborhoodId; }
    public void setNeighbourhoodId(Long neighborhoodId) { this.neighborhoodId = neighborhoodId; }

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

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}