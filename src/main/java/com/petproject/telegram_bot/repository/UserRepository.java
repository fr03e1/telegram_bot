package com.petproject.telegram_bot.repository;

import com.petproject.telegram_bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTgId(Long telegramId);
}