package com.drivingschool.mapper;

import com.drivingschool.domain.Answer;
import com.drivingschool.domain.Question;
import com.drivingschool.dto.AnswerDto;
import com.drivingschool.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    Answer toEntity(AnswerDto source);
    AnswerDto toDto(Answer source);
}
