package com.neighbourly.neighbourhoodservice.handler;

import com.neighbourly.commonservice.dispatcher.CommandHandler;
import com.neighbourly.commonservice.errorhandling.Either;
import com.neighbourly.neighbourhoodservice.command.GetNeighbourhoodByIdCommand;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import com.neighbourly.neighbourhoodservice.entity.Neighbourhood;
import com.neighbourly.neighbourhoodservice.exception.NeighbourhoodException;
import com.neighbourly.neighbourhoodservice.repository.NeighbourhoodRepository;
import org.springframework.stereotype.Component;

@Component
public class GetNeighbourhoodByIdCommandHandler implements CommandHandler<GetNeighbourhoodByIdCommand, NeighbourhoodResponseDTO> {

    private final NeighbourhoodRepository neighbourhoodRepository;

    public GetNeighbourhoodByIdCommandHandler(NeighbourhoodRepository neighbourhoodRepository) {
        this.neighbourhoodRepository = neighbourhoodRepository;
    }

    @Override
    public Either<String, NeighbourhoodResponseDTO> handle(GetNeighbourhoodByIdCommand command) {
        try {
            Neighbourhood neighbourhood = neighbourhoodRepository.findById(command.getNeighbourhoodId())
                    .orElseThrow(() -> new NeighbourhoodException("Neighbourhood not found with ID: " + command.getNeighbourhoodId()));

            return Either.right(mapToResponse(neighbourhood));
        } catch (NeighbourhoodException e) {
            return Either.left(e.getMessage());
        } catch (Exception e) {
            return Either.left("Failed to fetch neighbourhood: " + e.getMessage());
        }
    }

    private NeighbourhoodResponseDTO mapToResponse(Neighbourhood neighbourhood) {
        NeighbourhoodResponseDTO response = new NeighbourhoodResponseDTO();
        response.setNeighbourhoodId(neighbourhood.getNeighbourhoodId());
        response.setName(neighbourhood.getName());
        response.setDescription(neighbourhood.getDescription());
        response.setLocation(neighbourhood.getLocation());
        response.setCreatedAt(neighbourhood.getCreatedAt());
        return response;
    }
}