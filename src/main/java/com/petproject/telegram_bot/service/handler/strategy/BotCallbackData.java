package com.petproject.telegram_bot.service.handler.strategy;

import lombok.Getter;

@Getter
public enum BotCallbackData {
    TEST("test");
    private final String value;
    BotCallbackData(String value) {
        this.value = value;
    }
}
