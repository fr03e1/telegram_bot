package com.petproject.telegram_bot.service.creator.script;

import com.petproject.telegram_bot.model.BotCallbackData;
import com.petproject.telegram_bot.service.creator.button.BotButtonCreator;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class ScriptCreatorImpl implements ScriptCreator {
    private Map<String, InlineKeyboardMarkup> scriptMap;
    private final BotButtonCreator botInlineButtonCreator;

    public ScriptCreatorImpl(BotButtonCreator botInlineButtonCreator) {
        this.botInlineButtonCreator = botInlineButtonCreator;
        this.scriptMap = this.initScript();
    }

    private Map<String, InlineKeyboardMarkup> initScript() {
        this.scriptMap = new HashMap<>();

        this.scriptMap.put(BotCallbackData.DEFAULT.toString(),
                this.botInlineButtonCreator.createInlineKeyboardMarkup(
                        this.botInlineButtonCreator.createInlineKeyboardButton("Стартуем", BotCallbackData.TEST)
                )
        );

        this.scriptMap.put(BotCallbackData.TEST.toString(),
                this.botInlineButtonCreator.createInlineKeyboardMarkup(
                        this.botInlineButtonCreator.createInlineKeyboardButton("Стартуем", BotCallbackData.TEST)
                )
        );

        return scriptMap;
    }
}