package com.petproject.telegram_bot.service.creator.script;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Map;

public interface ScriptCreator {
    Map<String, InlineKeyboardMarkup> getScriptMap();
}
