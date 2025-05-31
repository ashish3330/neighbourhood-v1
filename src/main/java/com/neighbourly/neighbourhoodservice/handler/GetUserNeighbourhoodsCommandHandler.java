package com.neighbourly.neighbourhoodservice.handler;

import com.neighbourly.commonservice.dispatcher.CommandHandler;
import com.neighbourly.commonservice.errorhandling.Either;
import com.neighbourly.neighbourhoodservice.command.GetUserNeighbourhoodsCommand;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import com.neighbourly.neighbourhoodservice.entity.Neighbourhood;
import com.neighbourly.neighbourhoodservice.repository.NeighbourhoodRepository;
import com.neighbourly.neighbourhoodservice.repository.UserNeighbourhoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUserNeighbourhoodsCommandHandler implements CommandHandler<GetUserNeighbourhoodsCommand, List<NeighbourhoodResponseDTO>> {

    private static final Logger logger = LoggerFactory.getLogger(GetUserNeighbourhoodsCommandHandler.class);

    private final UserNeighbourhoodRepository userNeighbourhoodRepository;
    private final NeighbourhoodRepository neighbourhoodRepository;

    public GetUserNeighbourhoodsCommandHandler(UserNeighbourhoodRepository userNeighbourhoodRepository,
                                               NeighbourhoodRepository neighbourhoodRepository) {
        this.userNeighbourhoodRepository = userNeighbourhoodRepository;
        this.neighbourhoodRepository = neighbourhoodRepository;
    }

    @Override
    public Either<String, List<NeighbourhoodResponseDTO>> handle(GetUserNeighbourhoodsCommand command) {
        logger.info("Fetching neighbourhoods for user with ID: {}", command.getUserId());

        try {
            List<Long> neighbourhoodIds = userNeighbourhoodRepository.findNeighbourhoodIdsByUserId(command.getUserId());
            logger.debug("Found neighbourhood IDs: {}", neighbourhoodIds);

            List<Neighbourhood> neighbourhoods = neighbourhoodRepository.findAllById(neighbourhoodIds);
            logger.debug("Retrieved {} neighbourhoods from repository", neighbourhoods.size());

            List<NeighbourhoodResponseDTO> response = neighbourhoods.stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());

            logger.info("Successfully fetched {} neighbourhoods for user with ID: {}", response.size(), command.getUserId());
            return Either.right(response);
        } catch (Exception e) {
            logger.error("Failed to fetch user neighbourhoods for userId {}: {}", command.getUserId(), e.getMessage(), e);
            return Either.left("Failed to fetch user neighbourhoods: " + e.getMessage());
        }
    }

    private NeighbourhoodResponseDTO mapToResponse(Neighbourhood neighbourhood) {
        NeighbourhoodResponseDTO response = new NeighbourhoodResponseDTO();
        response.setNeighbourhoodId(neighbourhood.getNeighbourhoodId());
        response.setName(neighbourhood.getName());
        response.setDescription(neighbourhood.getDescription());
        response.setLocation(neighbourhood.getLocation());
        response.setLatitude(neighbourhood.getLatitude());
        response.setLongitude(neighbourhood.getLongitude());
        response.setCreatedAt(neighbourhood.getCreatedAt());
        return response;
    }
}
