package com.petproject.telegram_bot.repository;

import com.petproject.telegram_bot.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}