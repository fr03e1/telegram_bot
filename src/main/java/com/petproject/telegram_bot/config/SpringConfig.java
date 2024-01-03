package com.petproject.telegram_bot.config;

import com.petproject.telegram_bot.Bot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class SpringConfig {
    @Bean
    public TelegramBotsApi telegramBotsApi(Bot bot) throws TelegramApiException {
        final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        Bot.BOT_ID = bot.getMe().getId();
        return telegramBotsApi;
    }
}
