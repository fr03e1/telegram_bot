package com.petproject.telegram_bot.constant;

import lombok.Getter;

@Getter
public enum Command {
    START("start");

    private final String value;
    Command(String value) {
        this.value = value;
    }
}
