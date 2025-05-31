package com.neighbourly.neighbourhoodservice.command;

import com.neighbourly.commonservice.dispatcher.Command;
import com.neighbourly.neighbourhoodservice.dto.NeighbourhoodResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class GetUserNeighbourhoodsCommand extends Command<List<NeighbourhoodResponseDTO>> {
    private final Long userId;

    public GetUserNeighbourhoodsCommand(Long userId) {
        this.userId = userId;
    }

}