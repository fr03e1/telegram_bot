package com.petproject.telegram_bot.service.handler.strategy;

import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.model.message.TextMessage;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
@Service(MessageType.COMMAND)
public class CommandHandlerStrategy implements HandlerStrategy{
    @Override
    public MessageData processMessage(Update update) {
        System.out.println("World");
        return new TextMessage(String.valueOf(update.getMessage().getChatId()), "From where");
    }
}