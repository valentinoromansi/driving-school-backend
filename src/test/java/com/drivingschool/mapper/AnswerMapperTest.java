package com.drivingschool.mapper;

import com.drivingschool.domain.Answer;
import com.drivingschool.repository.DatabaseInitializer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerMapperTest {
    private AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);

    @Test
    void sourceToEntityTest() {
        DatabaseInitializer.AnswerSource answerSource = new DatabaseInitializer.AnswerSource("Answer text", true);
        Answer answer = answerMapper.sourceToEntity(answerSource);
        assertEquals(answer.getCorrect(), answerSource.correct(), "'Question.correct' not mapped correctly.");
        assertEquals(answer.getText(), answerSource.text(), "'Question.text' not mapped correctly.");
    }

    @Test
    void sourcesToEntitiesTest() {
        List<DatabaseInitializer.AnswerSource> answerSources = List.of(
            new DatabaseInitializer.AnswerSource("Answer text1", true),
            new DatabaseInitializer.AnswerSource("Answer text2", false)
        );
        List<Answer> answers = answerMapper.sourcesToEntities(answerSources);
        assertEquals(answers.get(0).getText(), answerSources.get(0).text(), "'Question[0].text' not mapped correctly.");
        assertEquals(answers.get(0).getCorrect(), answerSources.get(0).correct(), "'Question[0].correct' not mapped correctly.");
        assertEquals(answers.get(1).getText(), answerSources.get(1).text(), "'Question[1].text' not mapped correctly.");
        assertEquals(answers.get(1).getCorrect(), answerSources.get(1).correct(), "'Question[1].correct' not mapped correctly.");
    }
}