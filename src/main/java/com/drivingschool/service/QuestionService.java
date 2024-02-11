package com.drivingschool.service;

import com.drivingschool.domain.Question;
import com.drivingschool.exception.DatabaseResourceException;
import com.drivingschool.repository.QuestionRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        log.info(String.valueOf(question.getId()));
        return questionRepository.save(question);
    }
}
