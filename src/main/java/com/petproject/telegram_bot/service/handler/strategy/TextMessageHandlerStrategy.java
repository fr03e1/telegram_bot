package com.petproject.telegram_bot.service.handler.strategy;

import com.petproject.telegram_bot.Bot;
import com.petproject.telegram_bot.model.BotCallbackData;
import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.model.message.TextMessage;
import com.petproject.telegram_bot.service.creator.script.ScriptCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Service(MessageType.TEXT)
@Log4j2
@RequiredArgsConstructor
public class TextMessageHandlerStrategy implements HandlerStrategy {
    private final ScriptCreator scriptCreator;
    @Override
    public MessageData processMessage(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        MessageData message;

        if (update.getMessage().getText().substring(1).split(" ")[0].equals("start")) {
            message = this.createTextMessage(
                    chatId,
                    "Hello world",
                    this.scriptCreator.getScriptMap().get(BotCallbackData.TEST.getData())
            );
        } else {
            message = this.createTextMessage(chatId, "default", null);
        }

        return message;
    }

    private MessageData createTextMessage(String chatId, String message, ReplyKeyboard keyboard) {
        return new TextMessage(chatId, message, keyboard);
    }
}