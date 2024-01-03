package com.petproject.telegram_bot.service.handler.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class HandlerFactory {
    private final Map<String, HandlerStrategy> handlerStrategyMap;
    public HandlerStrategy getHandlerStrategy(String type) {
        final HandlerStrategy handlerStrategy = handlerStrategyMap.get(type);
        if(null == handlerStrategy) {
            throw new RuntimeException("Unsupported notification type");
        }
        return handlerStrategy;
    }
}