package com.petproject.telegram_bot.service.handler;

import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.service.handler.strategy.HandlerFactory;
import com.petproject.telegram_bot.service.handler.strategy.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Log4j2
@Service
@RequiredArgsConstructor
public class BotMessageHandlerImpl implements BotMessageHandler {
    private final HandlerFactory handlerFactory;
    @Override
    public MessageData handleMessage(Update update) {
       String type;

       if(update.hasMessage()) {
          type = MessageType.TEXT;
       } else if(update.hasCallbackQuery()) {
          type = MessageType.CALLBACK;
       } else throw new RuntimeException("Unsupported message type");

       return this.handlerFactory.getHandlerStrategy(type).processMessage(update);
    }
}