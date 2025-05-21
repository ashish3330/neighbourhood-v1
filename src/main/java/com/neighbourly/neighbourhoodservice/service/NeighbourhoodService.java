package com.neighbourly.neighbourhoodservice.service;// Service: NeighbourhoodService.java (Updated)

import com.neighbourly.commonservice.dispatcher.Dispatcher;
import com.neighbourly.commonservice.errorhandling.Either;

import com.neighbourly.neighbourhoodservice.command.*;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodRequestDTO;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class NeighbourhoodService  {

    private final Dispatcher syncDispatcher;

    public NeighbourhoodService(Dispatcher syncDispatcher) {
        this.syncDispatcher = syncDispatcher;
    }

    public Either<String, NeighbourhoodResponseDTO> createNeighbourhood(Long userId, NeighbourhoodRequestDTO requestDTO) {
        return syncDispatcher.dispatch(new CreateNeighbourhoodCommand(userId, requestDTO));
    }

    public Either<String, Void> joinNeighbourhood(Long userId, Long neighborhoodId) {
        return syncDispatcher.dispatch(new JoinNeighbourhoodCommand(userId, neighborhoodId));
    }

    public Either<String, List<NeighbourhoodResponseDTO>> getUserNeighbourhoods(Long userId) {
        return syncDispatcher.dispatch(new GetUserNeighbourhoodsCommand(userId));
    }

    public Either<String, NeighbourhoodResponseDTO> getNeighbourhoodById(Long neighborhoodId) {
        return syncDispatcher.dispatch(new GetNeighbourhoodByIdCommand(neighborhoodId));
    }

    public Either<String, List<NeighbourhoodResponseDTO>> getNearbyNeighbourhoods(Long userId, Double latitude, Double longitude, double radiusKm) {
        return syncDispatcher.dispatch(new GetNearbyNeighbourhoodsCommand(userId, latitude, longitude, radiusKm));
    }


}