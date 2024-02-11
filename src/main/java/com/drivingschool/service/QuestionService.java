package com.drivingschool.service;

import com.drivingschool.domain.Question;
import com.drivingschool.exception.DatabaseResourceException;
import com.drivingschool.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Page<Question> findAll(String textSubstring, Pageable pageable) {
        return questionRepository.findAllByTextContaining(textSubstring, pageable);
    }

    public Question save(Question question) throws RuntimeException {
        boolean alreadyExist = questionRepository.findOneByText(question.getText()).isPresent();
        if (alreadyExist)
            throw new DatabaseResourceException("Question with text '" + question.getText() + "' already exists.");
        return questionRepository.save(question);
    }
}
