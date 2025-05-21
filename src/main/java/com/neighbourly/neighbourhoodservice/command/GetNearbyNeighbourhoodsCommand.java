package com.neighbourly.neighbourhoodservice.command;// Command: GetNearbyNeighbourhoodsCommand.java

import com.neighbourly.commonservice.dispatcher.Command;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;

import java.util.List;

public class GetNearbyNeighbourhoodsCommand extends Command<List<NeighbourhoodResponseDTO>> {
    private final Long userId;
    private final Double latitude;
    private final Double longitude;
    private final double radiusKm;

    public GetNearbyNeighbourhoodsCommand(Long userId, Double latitude, Double longitude, double radiusKm) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radiusKm = radiusKm;
    }

    public Long getUserId() { return userId; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public double getRadiusKm() { return radiusKm; }
}