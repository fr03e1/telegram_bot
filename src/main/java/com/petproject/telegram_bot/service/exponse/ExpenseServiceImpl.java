package com.petproject.telegram_bot.service.exponse;

import com.petproject.telegram_bot.model.Category;
import com.petproject.telegram_bot.model.Expense;
import com.petproject.telegram_bot.repository.CategoryRepository;
import com.petproject.telegram_bot.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public void saveExpenseRecord(String value, String category) {
        this.expenseRepository.save(
                Expense.builder()
                        .value(new BigDecimal(value))
                        .category(this.categoryRepository.findByKey(category))
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}
