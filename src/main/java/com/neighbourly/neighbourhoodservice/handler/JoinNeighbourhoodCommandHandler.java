package com.neighbourly.neighbourhoodservice.handler;

import com.neighbourly.commonservice.dispatcher.CommandHandler;
import com.neighbourly.commonservice.errorhandling.Either;

import com.neighbourly.neighbourhoodservice.command.JoinNeighbourhoodCommand;
import com.neighbourly.neighbourhoodservice.entity.UserNeighbourhood;
import com.neighbourly.neighbourhoodservice.entity.Neighbourhood;
import com.neighbourly.neighbourhoodservice.exception.NeighbourhoodException;
import com.neighbourly.neighbourhoodservice.repository.NeighbourhoodRepository;
import com.neighbourly.neighbourhoodservice.repository.UserNeighbourhoodRepository;
import org.springframework.stereotype.Component;

@Component
public class JoinNeighbourhoodCommandHandler implements CommandHandler<JoinNeighbourhoodCommand, Void> {

    private final NeighbourhoodRepository neighborhoodRepository;
    private final UserNeighbourhoodRepository userNeighbourhoodRepository;

    public JoinNeighbourhoodCommandHandler(NeighbourhoodRepository neighborhoodRepository,
                                          UserNeighbourhoodRepository userNeighbourhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
        this.userNeighbourhoodRepository = userNeighbourhoodRepository;
    }

    @Override
    public Either<String, Void> handle(JoinNeighbourhoodCommand command) {
        try {
            Long userId = command.getUserId();
            Long neighborhoodId = command.getNeighbourhoodId();

            if (userId == null) {
                return Either.left("User ID is required");
            }

            Neighbourhood neighborhood = neighborhoodRepository.findById(neighborhoodId)
                    .orElseThrow(() -> new NeighbourhoodException("Neighbourhood not found with ID: " + neighborhoodId));

            // Check if user is already in the neighborhood
            if (userNeighbourhoodRepository.findByUserIdAndNeighbourhoodId(userId, neighborhoodId).isPresent()) {
                return Either.left("User already in neighborhood");
            }

            UserNeighbourhood userNeighbourhood = new UserNeighbourhood();
            userNeighbourhood.setUserId(userId);
            userNeighbourhood.setNeighbourhoodId(neighborhoodId);
            userNeighbourhoodRepository.save(userNeighbourhood);

            return Either.right(null);
        } catch (NeighbourhoodException e) {
            return Either.left(e.getMessage());
        } catch (Exception e) {
            return Either.left("Failed to join neighborhood: " + e.getMessage());
        }
    }
}