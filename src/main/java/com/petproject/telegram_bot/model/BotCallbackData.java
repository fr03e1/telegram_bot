package com.petproject.telegram_bot.model;

import lombok.Getter;

@Getter
public enum BotCallbackData {
    DEFAULT("default"),
    START("start"),
    CATEGORY("category"),
    EXPENSE("expense"),

    PRODUCTS("products"),
    TRANSPORT("transport"),
    HOUSE_SERVICE("house_service"),
    EAT_OUTSIDE("eat_outside"),
    CLOTHES("clothes"),
    OTHERS("others");

    private final String data;

    BotCallbackData(String data) {
        this.data = data;
    }
}
