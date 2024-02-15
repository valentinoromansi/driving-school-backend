package com.drivingschool.repository;

import com.drivingschool.domain.*;
import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.drivingschool.domain.enumeration.ResourceTypeE;
import com.drivingschool.service.QuestionService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer {

    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answersRepository;
    private final ResourceTypeRepository resourceTypeRepository;
    private final ResourceRepository resourceRepository;

    public DatabaseInitializer(
            QuestionTypeRepository questionTypeRepository,
            QuestionRepository questionRepository,
            AnswerRepository answersRepository, ResourceTypeRepository resourceTypeRepository, ResourceRepository resourceRepository
    ) {
        this.questionTypeRepository = questionTypeRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
        this.resourceTypeRepository = resourceTypeRepository;
        this.resourceRepository = resourceRepository;
    }

    @PostConstruct
    public void init() {
        for (QuestionTypeE type : QuestionTypeE.values()) {
            QuestionType questionType = new QuestionType(type);
            questionTypeRepository.save(questionType);
        }
        for (ResourceTypeE type : ResourceTypeE.values()) {
            ResourceType resourceType = new ResourceType(type);
            resourceTypeRepository.save(resourceType);
        }
    }

    @PostConstruct
    public void mockInitQuestions() {
        /**
         * Resources
         */
        if(resourceRepository.findById(1L).isEmpty()) {
            Resource resource = Resource.builder()
                    .id(1L)
                    .uri("https://autoskola-ispiti.com/images/medium/g6649_1.png")
                    .resourceType(new ResourceType(ResourceTypeE.IMAGE))
                    .build();
            resourceRepository.save(resource);
        }
        if(resourceRepository.findById(2L).isEmpty()) {
            Resource resource = Resource.builder()
                    .id(2L)
                    .uri("https://autoskola-ispiti.com/images/medium/g6654_1.png")
                    .resourceType(new ResourceType(ResourceTypeE.IMAGE))
                    .build();
            resourceRepository.save(resource);
        }
        /**
         * Questions
         */
        if(questionRepository.findById(1L).isEmpty()) {
            Question question = Question.builder()
                    .id(1L)
                    .text("Question 1?")
                    .explanation("explanation")
                    .questionType(new QuestionType(QuestionTypeE.SELECTED))
                    .resources(List.of(
                            Resource.builder().id(1L).build(),
                            Resource.builder().id(2L).build()
                    ))
                    .build();
            questionRepository.save(question);
        }
        if(questionRepository.findById(2L).isEmpty()) {
            Question question = Question.builder()
                    .id(2L)
                    .text("Question 2?")
                    .explanation("explanation2")
                    .questionType(new QuestionType(QuestionTypeE.TYPED))
                    .build();
            questionRepository.save(question);
        }
        /**
         * Answers
         */
        if (answersRepository.findById(1L).isEmpty()) {
            Answer answer = Answer.builder()
                    .id(1L)
                    .text("Answer 1")
                    .correct(true)
                    .question(Question.builder().id(1L).build())
                    .build();
            answersRepository.save(answer);
        }
        if (answersRepository.findById(2L).isEmpty()) {
            Answer answer = Answer.builder()
                    .id(2L)
                    .text("Answer 2")
                    .correct(false)
                    .question(Question.builder().id(1L).build())
                    .build();
            answersRepository.save(answer);
        }
        if (answersRepository.findById(3L).isEmpty()) {
            Answer answer = Answer.builder()
                    .id(3L)
                    .text("Answer 3")
                    .correct(true)
                    .question(Question.builder().id(2L).build())
                    .build();
            answersRepository.save(answer);
        }
    }

}
