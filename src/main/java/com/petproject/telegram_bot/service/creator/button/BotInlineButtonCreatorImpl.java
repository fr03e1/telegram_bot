package com.petproject.telegram_bot.service.creator.button;

import com.petproject.telegram_bot.model.BotCallbackData;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BotInlineButtonCreatorImpl implements BotButtonCreator {
    @Override
    public InlineKeyboardMarkup createInlineKeyboardMarkup(InlineKeyboardButton... inlineKeyboardButton) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        List<InlineKeyboardButton> buttonsRow = new ArrayList<>(Arrays.asList(inlineKeyboardButton));

        rowList.add(buttonsRow);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup createInlineKeyboardMarkupWithNewRowOnEachButton(InlineKeyboardButton... inlineKeyboardButtons) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtons) {
            rowList.add(List.of(inlineKeyboardButton));
        }

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    @Override
    public InlineKeyboardButton createInlineKeyboardButton(String text, BotCallbackData botCallbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(botCallbackData.toString());
        return button;
    }

    @Override
    public InlineKeyboardMarkup addNewButtonRowToMarkup(InlineKeyboardMarkup inlineKeyboardMarkup, String text, BotCallbackData botCallbackData) {
        inlineKeyboardMarkup.getKeyboard().add(new ArrayList<>(List.of(this.createInlineKeyboardButton(text, botCallbackData))));
        return inlineKeyboardMarkup;
    }
}