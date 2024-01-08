package com.petproject.telegram_bot;

import com.petproject.telegram_bot.model.message.CategoryTextMessage;
import com.petproject.telegram_bot.model.message.MessageData;
import com.petproject.telegram_bot.model.message.TextMessage;
import com.petproject.telegram_bot.service.expense.ExpenseService;
import com.petproject.telegram_bot.service.handler.BotMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    private final BotMessageHandler botMessageHandler;
    private final ExpenseService expenseService;

    @Value("${bot.name}")
    private String name;
    @Value("${bot.token}")
    private String token;

    private String category = "";
    private Integer messageId = -1;
    public static long BOT_ID = -1;

    @Override
    public String getBotUsername() {
        return this.name;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        final MessageData messageData = this.botMessageHandler.handleMessage(update);

        if(this.messageId != - 1 && !this.category.isEmpty() && update.getUpdateId() == this.messageId + 1) {
            this.expenseService.saveExpenseRecord(update.getMessage().getText(), this.category);
            this.category = "";
            this.messageId = -1;
            return;
        }

        if(messageData instanceof TextMessage textMessage) {
            final SendMessage sendMessage = new SendMessage(textMessage.getChatId(), textMessage.getMessage());
            sendMessage.setReplyMarkup(textMessage.getInlineKeyboardMarkup());

            this.sendMessage(sendMessage);
        }else if(messageData instanceof CategoryTextMessage categoryTextMessage) {
            final SendMessage sendMessage = new SendMessage(categoryTextMessage.getChatId(), categoryTextMessage.getMessage());
            this.category = categoryTextMessage.getCategory();
            this.messageId = categoryTextMessage.getMessageId();

            this.sendMessage(sendMessage);
        }
    }

    private void sendMessage(BotApiMethod<?> method) {
        try {
             execute(method);
        }catch (TelegramApiException e) {
            log.error("", e);
        }
    }
}