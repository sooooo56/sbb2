package com.mysite.sbb;

import com.mysite.sbb.category.Category;
import com.mysite.sbb.category.CategoryRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    public DataInitializer(CategoryRepository categoryRepository, QuestionRepository questionRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Category programming = categoryRepository.save(new Category("Programming"));
            Category travel = categoryRepository.save(new Category("Travel"));

            Question question1 = new Question();
            question1.setSubject("What is Spring Boot?");
            question1.setContent("I want to learn about Spring Boot.");
            question1.setCreateDate(LocalDateTime.now());
            question1.setCategory(programming); // 카테고리 연결

            questionRepository.save(question1);
        }
    }
}
