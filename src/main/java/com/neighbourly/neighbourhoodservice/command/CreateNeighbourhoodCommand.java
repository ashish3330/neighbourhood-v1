package com.neighbourly.neighbourhoodservice.command;

import com.neighbourly.commonservice.dispatcher.Command;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodRequestDTO;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodRequestDTO;

public class CreateNeighbourhoodCommand extends Command<NeighbourhoodResponseDTO> {
    private final Long userId;
    private final NeighbourhoodRequestDTO requestDTO;

    public CreateNeighbourhoodCommand(Long userId, NeighbourhoodRequestDTO requestDTO) {
        this.userId = userId;
        this.requestDTO = requestDTO;
    }

    public Long getUserId() { return userId; }
    public NeighbourhoodRequestDTO getRequestDTO() { return requestDTO; }
}