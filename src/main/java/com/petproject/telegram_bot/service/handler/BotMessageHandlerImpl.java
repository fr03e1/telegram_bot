package com.petproject.telegram_bot.service.handler;

import com.petproject.telegram_bot.model.User;
import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.repository.UserRepository;
import com.petproject.telegram_bot.service.handler.strategy.HandlerFactory;
import com.petproject.telegram_bot.service.handler.strategy.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BotMessageHandlerImpl implements BotMessageHandler {
    private final HandlerFactory handlerFactory;
    private final UserRepository userRepository;
    @Override
    public MessageData handleMessage(Update update) {
       String type;

       if(update.hasMessage()) {
          type = MessageType.TEXT;
          this.getOrCreateUser(update.getMessage().getChatId(), update.getMessage().getFrom().getUserName());
       } else if(update.hasCallbackQuery()) {
          type = MessageType.CALLBACK;
          this.getOrCreateUser(update.getMessage().getChatId(), update.getCallbackQuery().getFrom().getUserName());
       } else throw new RuntimeException("Unsupported message type");

       return this.handlerFactory.getHandlerStrategy(type).processMessage(update);
    }

    private User getOrCreateUser(Long chatId, String userName) {
        Optional<User> optional = this.userRepository.findByTgId(chatId);
        return optional.orElseGet(() -> this.userRepository.save(User.builder().userName(userName).tgId(chatId).build()));
    }
}