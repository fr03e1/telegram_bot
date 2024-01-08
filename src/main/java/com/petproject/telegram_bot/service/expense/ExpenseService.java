package com.petproject.telegram_bot.service.expense;

public interface ExpenseService {
    void saveExpenseRecord(String value, String category);
}