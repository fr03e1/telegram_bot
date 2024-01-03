package com.petproject.telegram_bot.model;

import lombok.Getter;

@Getter
public enum BotCallbackData {
    DEFAULT("default"),
    TEST("test");

    private final String data;

    BotCallbackData(String data) {
        this.data = data;
    }

}
