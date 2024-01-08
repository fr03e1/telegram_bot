package com.petproject.telegram_bot.model;

import lombok.Getter;

@Getter
public enum BotCallbackData {
    DEFAULT("default"),
    START("start"),
    CATEGORY("category");

    private final String data;

    BotCallbackData(String data) {
        this.data = data;
    }

}
