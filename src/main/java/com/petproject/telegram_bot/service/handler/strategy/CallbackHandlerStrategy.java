package com.petproject.telegram_bot.service.handler.strategy;

import com.petproject.telegram_bot.model.BotCallbackData;
import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.model.message.TextMessage;
import com.petproject.telegram_bot.repository.TelegramMessageRepository;
import com.petproject.telegram_bot.service.creator.script.ScriptCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service(MessageType.CALLBACK)
@RequiredArgsConstructor
public class CallbackHandlerStrategy implements HandlerStrategy{
    private final ScriptCreator scriptCreator;
    private final TelegramMessageRepository telegramMessageRepository;
    @Override
    public MessageData processMessage(Update update) {
        final BotCallbackData botCallbackData = BotCallbackData.valueOf(update.getCallbackQuery().getData().toUpperCase());

        return new TextMessage(
                String.valueOf(update.getCallbackQuery().getMessage().getChatId()),
                this.telegramMessageRepository.findByKey(botCallbackData.getData()).getMessage(),
                this.scriptCreator.getScriptMap().get(botCallbackData.getData())
        );
    }
}