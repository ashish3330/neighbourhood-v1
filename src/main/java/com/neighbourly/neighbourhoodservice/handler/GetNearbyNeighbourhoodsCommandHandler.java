package com.neighbourly.neighbourhoodservice.handler;
// Handler: GetNearbyNeighbourhoodsCommandHandler.java

import com.neighbourly.commonservice.dispatcher.CommandHandler;
import com.neighbourly.commonservice.errorhandling.Either;
import com.neighbourly.neighbourhoodservice.command.GetNearbyNeighbourhoodsCommand;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import com.neighbourly.neighbourhoodservice.entity.Neighbourhood;
import com.neighbourly.neighbourhoodservice.repository.NeighbourhoodRepository;
import com.neighbourly.neighbourhoodservice.util.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetNearbyNeighbourhoodsCommandHandler implements CommandHandler<GetNearbyNeighbourhoodsCommand, List<NeighbourhoodResponseDTO>> {

    private final NeighbourhoodRepository neighborhoodRepository;

    public GetNearbyNeighbourhoodsCommandHandler(NeighbourhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    public Either<String, List<NeighbourhoodResponseDTO>> handle(GetNearbyNeighbourhoodsCommand command) {
        try {
            if (command.getUserId() == null) {
                return Either.left("User ID is required");
            }
            if (command.getLatitude() == null || command.getLongitude() == null) {
                return Either.left("User location (latitude and longitude) is required");
            }

            List<Neighbourhood> allNeighbourhoods = neighborhoodRepository.findAll();
            List<NeighbourhoodResponseDTO> nearbyNeighbourhoods = allNeighbourhoods.stream()
                    .filter(n -> n.getLatitude() != null && n.getLongitude() != null)
                    .filter(n -> DistanceCalculator.calculateDistance(
                            command.getLatitude(), command.getLongitude(),
                            n.getLatitude(), n.getLongitude()) <= command.getRadiusKm())
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());

            return Either.right(nearbyNeighbourhoods);
        } catch (Exception e) {
            return Either.left("Failed to fetch nearby neighborhoods: " + e.getMessage());
        }
    }

    private NeighbourhoodResponseDTO mapToResponse(Neighbourhood neighborhood) {
        NeighbourhoodResponseDTO response = new NeighbourhoodResponseDTO();
        response.setNeighbourhoodId(neighborhood.getNeighbourhoodId());
        response.setName(neighborhood.getName());
        response.setDescription(neighborhood.getDescription());
        response.setLocation(neighborhood.getLocation());
        response.setLatitude(neighborhood.getLatitude());
        response.setLongitude(neighborhood.getLongitude());
        response.setCreatedAt(neighborhood.getCreatedAt());
        return response;
    }
}