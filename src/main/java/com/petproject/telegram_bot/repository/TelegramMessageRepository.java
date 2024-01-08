package com.petproject.telegram_bot.repository;

import com.petproject.telegram_bot.model.TelegramMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramMessageRepository extends JpaRepository<TelegramMessage, Long> {
    TelegramMessage findByKey(String key);
}