package com.petproject.telegram_bot.service.handler.strategy;

import com.petproject.telegram_bot.model.message.MessageData;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface HandlerStrategy {
    MessageData processMessage(Update update);
}