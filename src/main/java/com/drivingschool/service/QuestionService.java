package com.drivingschool.service;

import com.drivingschool.domain.Question;
import com.drivingschool.dto.QuestionDTO;
import com.drivingschool.exception.DatabaseResourceException;
import com.drivingschool.mapper.QuestionMapper;
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
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public Page<Question> findAll(String textSubstring, Pageable pageable) {
        if(textSubstring != null)
            return questionRepository.findAllByTextContaining(textSubstring, pageable);
        return questionRepository.findAll(pageable);
    }

    public QuestionDTO save(QuestionDTO dto) throws RuntimeException {
        Question question = questionMapper.toEntity(dto);
        boolean alreadyExist = questionRepository.findOneByText(question.getText()).isPresent();
        if (alreadyExist)
            throw new DatabaseResourceException("Question with text '" + question.getText() + "' already exists.");
        log.info(String.valueOf(question.getId()));
        question = questionRepository.save(question);
        return questionMapper.toDto(question);
    }
}
