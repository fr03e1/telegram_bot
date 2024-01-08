package com.petproject.telegram_bot;

import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.model.message.TextMessage;
import com.petproject.telegram_bot.service.handler.BotMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    private final BotMessageHandler botMessageHandler;

    @Value("${bot.name}")
    private String name;
    @Value("${bot.token}")
    private String token;

    public static long BOT_ID = -1;

    @Override
    public String getBotUsername() {
        return this.name;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        final MessageData messageData = this.botMessageHandler.handleMessage(update);

        if(messageData instanceof TextMessage textMessage) {
            try {
                execute(new SendMessage(textMessage.getChatId(), textMessage.getMessage()));
            } catch (TelegramApiException e) {
                log.error("", e);
            }
        }
    }
}