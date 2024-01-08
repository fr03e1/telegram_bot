package com.petproject.telegram_bot.service.creator.script;

import com.petproject.telegram_bot.model.BotCallbackData;
import com.petproject.telegram_bot.repository.CategoryRepository;
import com.petproject.telegram_bot.service.creator.button.BotButtonCreator;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
public class ScriptCreatorImpl implements ScriptCreator {
    private Map<String, InlineKeyboardMarkup> scriptMap;
    private final BotButtonCreator botInlineButtonCreator;
    private final CategoryRepository categoryRepository;

    public ScriptCreatorImpl(BotButtonCreator botInlineButtonCreator, CategoryRepository categoryRepository) {
        this.botInlineButtonCreator = botInlineButtonCreator;
        this.categoryRepository = categoryRepository;

        this.scriptMap = this.initScript();
    }

    private Map<String, InlineKeyboardMarkup> initScript() {
        this.scriptMap = new HashMap<>();

        this.scriptMap.put(
                BotCallbackData.START.toString(),
                this.botInlineButtonCreator.createInlineKeyboardMarkup(
                        this.botInlineButtonCreator.createInlineKeyboardButton("Категории", BotCallbackData.CATEGORY),
                        this.botInlineButtonCreator.createInlineKeyboardButton("Отчет", BotCallbackData.START)
                )
        );

        this.scriptMap.put(
                BotCallbackData.CATEGORY.toString(),
                this.botInlineButtonCreator.createInlineKeyboardMarkup(
                        this.getCategoriesButtons()
                )
        );

        return scriptMap;
    }

    private List<InlineKeyboardButton> getCategoriesButtons() {
        return this.categoryRepository.findAll()
                .stream()
                .map(item -> this.botInlineButtonCreator.createInlineKeyboardButton(item.getValue(), BotCallbackData.valueOf(item.getKey().toUpperCase())))
                .toList();
    }
}