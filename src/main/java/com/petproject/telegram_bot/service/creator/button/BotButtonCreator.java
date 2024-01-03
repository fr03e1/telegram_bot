package com.petproject.telegram_bot.service.creator.button;

import com.petproject.telegram_bot.model.BotCallbackData;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface BotButtonCreator {
    InlineKeyboardButton createInlineKeyboardButton(String text, BotCallbackData botCallbackData);

    InlineKeyboardMarkup createInlineKeyboardMarkup(InlineKeyboardButton... inlineKeyboardButton);

    InlineKeyboardMarkup createInlineKeyboardMarkupWithNewRowOnEachButton(InlineKeyboardButton... inlineKeyboardButtons);

    InlineKeyboardMarkup addNewButtonRowToMarkup(InlineKeyboardMarkup inlineKeyboardMarkup, String text, BotCallbackData botCallbackData);
}