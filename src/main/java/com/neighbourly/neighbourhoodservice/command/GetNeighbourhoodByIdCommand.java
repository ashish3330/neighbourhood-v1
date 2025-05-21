package com.neighbourly.neighbourhoodservice.command;

import com.neighbourly.commonservice.dispatcher.Command;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;

public class GetNeighbourhoodByIdCommand extends Command<NeighbourhoodResponseDTO> {
    private final Long neighbourhoodId;

    public GetNeighbourhoodByIdCommand(Long neighborhoodId) {
        this.neighbourhoodId = neighborhoodId;
    }

    public Long getNeighbourhoodId() { return neighbourhoodId; }
}