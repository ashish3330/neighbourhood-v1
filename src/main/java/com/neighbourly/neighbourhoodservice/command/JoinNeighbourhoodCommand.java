package com.neighbourly.neighbourhoodservice.command;

import com.neighbourly.commonservice.dispatcher.Command;
import lombok.Getter;

@Getter
public class JoinNeighbourhoodCommand extends Command<String> {
    private final Long userId;
    private final Long neighbourhoodId;

    public JoinNeighbourhoodCommand(Long userId, Long neighborhoodId) {
        this.userId = userId;
        this.neighbourhoodId = neighborhoodId;
    }

}