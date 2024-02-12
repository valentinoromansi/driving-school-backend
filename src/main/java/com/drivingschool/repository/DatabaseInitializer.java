package com.drivingschool.repository;

import com.drivingschool.domain.Answer;
import com.drivingschool.domain.Question;
import com.drivingschool.domain.QuestionType;
import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.drivingschool.service.QuestionService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionRepository questionRepository;
    private AnswerRepository answersRepository;

    public DatabaseInitializer(
            QuestionTypeRepository questionTypeRepository,
            QuestionRepository questionRepository,
            AnswerRepository answersRepository
    ) {
        this.questionTypeRepository = questionTypeRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
    }

    @PostConstruct
    public void init() {
            for (QuestionTypeE type : QuestionTypeE.values()) {
                QuestionType questionType = new QuestionType(type);
                questionTypeRepository.save(questionType);
            }
    }

    @PostConstruct
    public void mockInitQuestions() {
        /**
         * Questions
         */
        if(questionRepository.findById(1L).isEmpty()) {
            Question question = Question.builder()
                    .id(1L)
                    .text("Question 1?")
                    .explanation("explanation")
                    .questionType(new QuestionType(QuestionTypeE.SELECTED))
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
