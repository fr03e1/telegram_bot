package com.petproject.telegram_bot.service.handler;

import com.petproject.telegram_bot.model.message.MessageData;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotMessageHandler {
    MessageData handleMessage(Update update);
}