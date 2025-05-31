package com.neighbourly.neighbourhoodservice.command;

import com.neighbourly.commonservice.dispatcher.Command;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import lombok.Getter;

@Getter
public class GetNeighbourhoodByIdCommand extends Command<NeighbourhoodResponseDTO> {
    private final Long neighbourhoodId;

    public GetNeighbourhoodByIdCommand(Long neighborhoodId) {
        this.neighbourhoodId = neighborhoodId;
    }

}