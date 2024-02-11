package com.drivingschool.repository;

import com.drivingschool.domain.QuestionType;
import com.drivingschool.domain.enumeration.QuestionTypeE;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final QuestionTypeRepository questionTypeRepository;

    public DatabaseInitializer(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    @PostConstruct
    public void init() {
            for (QuestionTypeE type : QuestionTypeE.values()) {
                QuestionType questionType = new QuestionType(type);
                questionTypeRepository.save(questionType);
            }
    }
}
