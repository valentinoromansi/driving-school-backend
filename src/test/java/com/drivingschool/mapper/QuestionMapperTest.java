package com.drivingschool.mapper;

import com.drivingschool.domain.Question;
import com.drivingschool.domain.QuestionType;
import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.drivingschool.dto.QuestionDto;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {QuestionMapper.class})
public class QuestionMapperTest {

    QuestionMapper mapper = Mappers.getMapper(QuestionMapper.class);

    @Test
    public void toDto() {
        Question entity = Question.builder()
                .id(1L)
                .text("text")
                .explanation("explanation")
                .questionType(new QuestionType(QuestionTypeE.SELECTED))
                .build();

        QuestionDto dto = mapper.toDto(entity);

        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getText()).isEqualTo(dto.getText());
        assertThat(entity.getExplanation()).isEqualTo(dto.getExplanation());
        assertThat(entity.getQuestionType().getId()).isEqualTo(dto.getQuestionType());
    }

    @Test
    public void toEntity() {
        QuestionDto dto = QuestionDto.builder()
                .id(1L)
                .text("text")
                .explanation("explanation")
                .questionType(QuestionTypeE.SELECTED)
                .build();

        Question entity = mapper.toEntity(dto);

        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getText()).isEqualTo(entity.getText());
        assertThat(dto.getExplanation()).isEqualTo(entity.getExplanation());
        assertThat(dto.getQuestionType()).isEqualTo(entity.getQuestionType().getId());
    }
}