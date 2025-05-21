package com.neighbourly.neighbourhoodservice.command;

import com.neighbourly.commonservice.dispatcher.Command;

public class JoinNeighbourhoodCommand extends Command<Void> {
    private final Long userId;
    private final Long neighborhoodId;

    public JoinNeighbourhoodCommand(Long userId, Long neighborhoodId) {
        this.userId = userId;
        this.neighborhoodId = neighborhoodId;
    }

    public Long getUserId() { return userId; }
    public Long getNeighbourhoodId() { return neighborhoodId; }
}