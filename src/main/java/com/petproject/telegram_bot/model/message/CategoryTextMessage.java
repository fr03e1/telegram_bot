package com.petproject.telegram_bot.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryTextMessage implements MessageData {
    private String chatId;
    private String message;
    private String category;
    private Integer messageId;
}