package com.petproject.telegram_bot.repository;

import com.petproject.telegram_bot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
