package com.petproject.telegram_bot.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextMessage implements MessageData {
    private String chatId;
    private String message;
    private ReplyKeyboard inlineKeyboardMarkup;

    public TextMessage(String chatId, String message) {
        this.chatId = chatId;
        this.message = message;
    }
}
