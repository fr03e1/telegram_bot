package com.petproject.telegram_bot.service.handler.strategy;

import com.petproject.telegram_bot.model.BotCallbackData;
import com.petproject.telegram_bot.model.Category;
import com.petproject.telegram_bot.model.message.CategoryTextMessage;
import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.model.message.TextMessage;
import com.petproject.telegram_bot.repository.CategoryRepository;
import com.petproject.telegram_bot.repository.TelegramMessageRepository;
import com.petproject.telegram_bot.service.creator.script.ScriptCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service(MessageType.CALLBACK)
@RequiredArgsConstructor
public class CallbackHandlerStrategy implements HandlerStrategy{
    private final ScriptCreator scriptCreator;
    private final TelegramMessageRepository telegramMessageRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public MessageData processMessage(Update update) {
        final BotCallbackData botCallbackData = BotCallbackData.valueOf(update.getCallbackQuery().getData().toUpperCase());

        if(this.isCategory(botCallbackData.getData())) {
            return new CategoryTextMessage(
                    String.valueOf(update.getCallbackQuery().getMessage().getChatId()),
                    this.telegramMessageRepository.findByKey(BotCallbackData.EXPENSE.getData()).getMessage(),
                    botCallbackData.getData(),
                    update.getUpdateId()
            );
        }

        return new TextMessage(
                String.valueOf(update.getCallbackQuery().getMessage().getChatId()),
                this.telegramMessageRepository.findByKey(botCallbackData.getData()).getMessage(),
                this.scriptCreator.getScriptMap().get(botCallbackData.toString())
        );
    }

    private boolean isCategory(String callbackData) {
        final List<String> categories =
                this.categoryRepository
                        .findAll()
                        .stream()
                        .map(Category::getKey)
                        .toList();

        return categories.contains(callbackData);
    }
}