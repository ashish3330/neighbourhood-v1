package com.neighbourly.neighbourhoodservice.handler;

import com.neighbourly.commonservice.dispatcher.CommandHandler;
import com.neighbourly.commonservice.errorhandling.Either;

import com.neighbourly.neighbourhoodservice.command.CreateNeighbourhoodCommand;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodRequestDTO;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import com.neighbourly.neighbourhoodservice.entity.Neighbourhood;
import com.neighbourly.neighbourhoodservice.entity.UserNeighbourhood;
import com.neighbourly.neighbourhoodservice.repository.NeighbourhoodRepository;
import com.neighbourly.neighbourhoodservice.repository.UserNeighbourhoodRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateNeighbourhoodCommandHandler implements CommandHandler<CreateNeighbourhoodCommand, NeighbourhoodResponseDTO> {

    private final NeighbourhoodRepository neighborhoodRepository;
    private final UserNeighbourhoodRepository userNeighbourhoodRepository;

    public CreateNeighbourhoodCommandHandler(NeighbourhoodRepository neighborhoodRepository,
                                            UserNeighbourhoodRepository userNeighbourhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
        this.userNeighbourhoodRepository = userNeighbourhoodRepository;
    }

    @Override
    public Either<String, NeighbourhoodResponseDTO> handle(CreateNeighbourhoodCommand command) {
        try {
            Long userId = command.getUserId();
            if (userId == null) {
                return Either.left("User ID is required");
            }

            NeighbourhoodRequestDTO dto = command.getRequestDTO();
            if (dto.getName() == null || dto.getName().isEmpty()) {
                return Either.left("Neighbourhood name is required");
            }

            Neighbourhood neighborhood = new Neighbourhood();
            neighborhood.setName(dto.getName());
            neighborhood.setDescription(dto.getDescription());
            neighborhood.setLocation(dto.getLocation());
            neighborhood.setLatitude(dto.getLatitude());
            neighborhood.setLongitude(dto.getLongitude());

            neighborhood = neighborhoodRepository.save(neighborhood);

            UserNeighbourhood userNeighbourhood = new UserNeighbourhood();
            userNeighbourhood.setUserId(userId);
            userNeighbourhood.setNeighbourhoodId(neighborhood.getNeighbourhoodId());
            userNeighbourhoodRepository.save(userNeighbourhood);

            return Either.right(mapToResponse(neighborhood));
        } catch (Exception e) {
            return Either.left("Failed to create neighborhood: " + e.getMessage());
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