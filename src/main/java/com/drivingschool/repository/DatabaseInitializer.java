package com.drivingschool.repository;

import com.drivingschool.DrivingschoolApplication;
import com.drivingschool.domain.*;
import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.drivingschool.domain.enumeration.ResourceTypeE;
import com.drivingschool.mapper.AnswerMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
public class DatabaseInitializer {

    @Value("${spring.database.create-and-fill-tables}")
    private boolean createAndFillDbTables;

    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answersRepository;
    private final ResourceTypeRepository resourceTypeRepository;
    private final ResourceRepository resourceRepository;
    private final AnswerMapper answerMapper;

    public DatabaseInitializer(
            QuestionTypeRepository questionTypeRepository,
            QuestionRepository questionRepository,
            AnswerRepository answersRepository, ResourceTypeRepository resourceTypeRepository, ResourceRepository resourceRepository, AnswerMapper answerMapper
    ) {
        this.questionTypeRepository = questionTypeRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
        this.resourceTypeRepository = resourceTypeRepository;
        this.resourceRepository = resourceRepository;
        this.answerMapper = answerMapper;
    }

    //@PostConstruct
    public void initDbEnumTypes() {
        for (QuestionTypeE type : QuestionTypeE.values()) {
            QuestionType questionType = new QuestionType(type);
            questionTypeRepository.save(questionType);
        }
        for (ResourceTypeE type : ResourceTypeE.values()) {
            ResourceType resourceType = new ResourceType(type);
            resourceTypeRepository.save(resourceType);
        }
    }

    public record AnswerSource(String text, Boolean correct) {}
    private record QuestionSource(String question, List<AnswerSource> answers, QuestionTypeE type, List<String> imagesUrls) {}

    private List<QuestionSource> parseQuestionsFromSource() {
        List<QuestionSource> questions = new LinkedList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            questions = objectMapper
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(
                            DrivingschoolApplication.class.getResource("/questions.json"),
                            new TypeReference<>() {}
                    );
            // Log parsed info state
            System.out.println("Questions parsed = " + questions.size());
            for (QuestionTypeE type : QuestionTypeE.values())
                System.out.println("Questions[" + type + "] parsed = " + questions.stream().filter(q -> q.type.equals(type)).count());
        } catch (Exception e) {
            throw new RuntimeException("Parsing resource file '/questions.json' failed with '" + e.getMessage() + "'");
        }
        if(questions.isEmpty())
            log.warn("0 Questions parsed from parsed file '/questions.json'");

        log.info(questions.stream().findFirst());

        return questions;
    }

    @Transactional
    @PostConstruct
    public void mockInitQuestions() {
        if(!createAndFillDbTables)
            return;

        initDbEnumTypes();

        List<QuestionSource> questions = parseQuestionsFromSource();

        for (var questionSource : questions) {
            List<Resource> resources = questionSource.imagesUrls.stream().map(url ->
                    Resource.builder()
                            .uri(url)
                            .resourceType(new ResourceType(ResourceTypeE.IMAGE))
                            .build())
                    .toList();
            Question question = Question.builder()
                    .text(questionSource.question)
                    .questionType(new QuestionType(questionSource.type))
                    .build()
                    .addAnswers(answerMapper.sourcesToEntities(questionSource.answers))
                    .addResources(resources);
            questionRepository.save(question);
        }
    }

}
