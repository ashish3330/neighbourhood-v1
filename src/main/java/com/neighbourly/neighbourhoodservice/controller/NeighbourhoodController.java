package com.neighbourly.neighbourhoodservice.controller;// Controller: NeighbourhoodController.java (Updated)

import com.neighbourly.commonservice.errorhandling.Either;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodRequestDTO;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import com.neighbourly.neighbourhoodservice.service.NeighbourhoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/neighborhoods")
public class NeighbourhoodController {

    private final NeighbourhoodService neighborhoodService;

    public NeighbourhoodController(NeighbourhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @PostMapping
    public ResponseEntity<?> createNeighbourhood(
            @RequestBody NeighbourhoodRequestDTO dto,
            @RequestHeader("user-id") Long userId) {
        Either<String, NeighbourhoodResponseDTO> result = neighborhoodService.createNeighbourhood(userId, dto);
        return result.isRight()
                ? ResponseEntity.ok(result.getRight())
                : ResponseEntity.badRequest().body(result.getLeft());
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<?> joinNeighbourhood(
            @PathVariable("id") Long neighborhoodId,
            @RequestHeader("user-id") Long userId) {
        Either<String, Void> result = neighborhoodService.joinNeighbourhood(userId, neighborhoodId);
        return result.isRight()
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body(result.getLeft());
    }

    @GetMapping("/users/{userId}/neighborhoods")
    public ResponseEntity<?> getUserNeighbourhoods(@PathVariable("userId") Long userId) {
        Either<String, List<NeighbourhoodResponseDTO>> result = neighborhoodService.getUserNeighbourhoods(userId);
        return result.isRight()
                ? ResponseEntity.ok(result.getRight())
                : ResponseEntity.badRequest().body(result.getLeft());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNeighbourhoodById(@PathVariable("id") Long neighborhoodId) {
        Either<String, NeighbourhoodResponseDTO> result = neighborhoodService.getNeighbourhoodById(neighborhoodId);
        return result.isRight()
                ? ResponseEntity.ok(result.getRight())
                : ResponseEntity.badRequest().body(result.getLeft());
    }

    @GetMapping("/nearby")
    public ResponseEntity<?> getNearbyNeighbourhoods(
            @RequestHeader("user-id") Long userId,
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude) {
        Either<String, List<NeighbourhoodResponseDTO>> result = neighborhoodService.getNearbyNeighbourhoods(userId, latitude, longitude, 100.0);
        return result.isRight()
                ? ResponseEntity.ok(result.getRight())
                : ResponseEntity.badRequest().body(result.getLeft());
    }
}