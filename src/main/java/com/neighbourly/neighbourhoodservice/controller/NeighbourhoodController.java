package com.neighbourly.neighbourhoodservice.controller;// Controller: NeighbourhoodController.java (Updated)

import com.neighbourly.commonservice.errorhandling.Either;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodRequestDTO;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import com.neighbourly.neighbourhoodservice.security.SecurityUtils;
import com.neighbourly.neighbourhoodservice.service.NeighbourhoodService;
import com.neighbourly.neighbourhoodservice.util.ControllerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/neighborhoods")
public class NeighbourhoodController {

    private final NeighbourhoodService neighborhoodService;
    private  final SecurityUtils securityUtils;
    private  final ControllerUtil   controllerUtils;

    public NeighbourhoodController(NeighbourhoodService neighborhoodService, SecurityUtils securityUtils, ControllerUtil controllerUtils) {
        this.neighborhoodService = neighborhoodService;
        this.securityUtils = securityUtils;
        this.controllerUtils = controllerUtils;
    }

    @PostMapping
    public ResponseEntity<?> createNeighbourhood(
            @RequestBody NeighbourhoodRequestDTO dto) {
        Either<String, NeighbourhoodResponseDTO> result = neighborhoodService.createNeighbourhood(securityUtils.getCurrentUserId(), dto);
        return controllerUtils.toResponseEntity(result);
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<?> joinNeighbourhood(Long neighborhoodId) {
        Either<String, Void> result = neighborhoodService.joinNeighbourhood(securityUtils.getCurrentUserId(), neighborhoodId);
        return controllerUtils.toResponseEntity(result);
    }

    @GetMapping("/users/{userId}/neighborhoods")
    public ResponseEntity<?> getUserNeighbourhoods(@PathVariable("userId") Long userId) {
        Either<String, List<NeighbourhoodResponseDTO>> result = neighborhoodService.getUserNeighbourhoods(userId);
        return controllerUtils.toResponseEntity(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNeighbourhoodById(@PathVariable("id") Long neighborhoodId) {
        Either<String, NeighbourhoodResponseDTO> result = neighborhoodService.getNeighbourhoodById(neighborhoodId);
        return controllerUtils.toResponseEntity(result);
    }

    @GetMapping("/nearby")
    public ResponseEntity<?> getNearbyNeighbourhoods(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude) {

        Either<String, List<NeighbourhoodResponseDTO>> result = neighborhoodService.getNearbyNeighbourhoods(securityUtils.getCurrentUserId(), latitude, longitude, 100.0);
        return controllerUtils.toResponseEntity(result);
    }
}