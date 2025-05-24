package com.neighbourly.neighbourhoodservice.config;


import com.neighbourly.commonservice.dispatcher.registry.HandlerRegistry;
import com.neighbourly.neighbourhoodservice.command.*;
import com.neighbourly.neighbourhoodservice.handler.*;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NeighbourhoodServiceConfig {

    private final HandlerRegistry handlerRegistry;
    private final CreateNeighbourhoodCommandHandler createNeighbourhoodCommandHandler;
    private final GetNearbyNeighbourhoodsCommandHandler getNearbyNeighbourhoodsCommandHandler;
    private final GetNeighbourhoodByIdCommandHandler getNeighbourhoodByIdCommandHandler;
    private final GetUserNeighbourhoodsCommandHandler getUserNeighbourhoodsCommandHandler;
    private final JoinNeighbourhoodCommandHandler   joinNeighbourhoodCommandHandler;


    public NeighbourhoodServiceConfig(HandlerRegistry handlerRegistry, CreateNeighbourhoodCommandHandler createNeighbourhoodCommandHandler, GetNearbyNeighbourhoodsCommandHandler getNearbyNeighbourhoodsCommandHandler, GetNeighbourhoodByIdCommandHandler getNeighbourhoodByIdCommandHandler, GetUserNeighbourhoodsCommandHandler getUserNeighbourhoodsCommandHandler, JoinNeighbourhoodCommandHandler joinNeighbourhoodCommandHandler) {
        this.handlerRegistry = handlerRegistry;
        this.createNeighbourhoodCommandHandler = createNeighbourhoodCommandHandler;
        this.getNearbyNeighbourhoodsCommandHandler = getNearbyNeighbourhoodsCommandHandler;
        this.getNeighbourhoodByIdCommandHandler = getNeighbourhoodByIdCommandHandler;
        this.getUserNeighbourhoodsCommandHandler = getUserNeighbourhoodsCommandHandler;
        this.joinNeighbourhoodCommandHandler = joinNeighbourhoodCommandHandler;
    }

    @PostConstruct
    public   void registerHandlers() {
        handlerRegistry.registerHandler( CreateNeighbourhoodCommand.class,createNeighbourhoodCommandHandler);
        handlerRegistry.registerHandler( GetNearbyNeighbourhoodsCommand.class,getNearbyNeighbourhoodsCommandHandler);
        handlerRegistry.registerHandler( GetNeighbourhoodByIdCommand.class,getNeighbourhoodByIdCommandHandler);
        handlerRegistry.registerHandler( GetUserNeighbourhoodsCommand.class,getUserNeighbourhoodsCommandHandler);
        handlerRegistry.registerHandler( JoinNeighbourhoodCommand.class,joinNeighbourhoodCommandHandler);

    }
}
