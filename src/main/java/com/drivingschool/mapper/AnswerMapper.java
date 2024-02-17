package com.drivingschool.mapper;

import com.drivingschool.domain.Answer;
import com.drivingschool.domain.Question;
import com.drivingschool.dto.AnswerDto;
import com.drivingschool.dto.QuestionDto;
import com.drivingschool.repository.DatabaseInitializer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer toEntity(AnswerDto source);
    AnswerDto toDto(Answer source);
    Answer sourceToEntity(DatabaseInitializer.AnswerSource source);
    List<Answer> sourcesToEntities(List<DatabaseInitializer.AnswerSource> source);
}
