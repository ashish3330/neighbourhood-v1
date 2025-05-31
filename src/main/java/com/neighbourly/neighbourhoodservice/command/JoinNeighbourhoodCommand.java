package com.neighbourly.neighbourhoodservice.command;

import com.neighbourly.commonservice.dispatcher.Command;
import lombok.Getter;

public class JoinNeighbourhoodCommand extends Command<Void> {
    @Getter
    private final Long userId;
    @Getter
    private final Long neighborhoodId;

    public JoinNeighbourhoodCommand(Long userId, Long neighborhoodId) {
        this.userId = userId;
        this.neighborhoodId = neighborhoodId;
    }

}