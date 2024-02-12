package com.drivingschool.mapper;

import com.drivingschool.domain.Question;
import com.drivingschool.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "questionType", target = "questionType.id")
    Question toEntity(QuestionDto source);
    @Mapping(source = "questionType.id", target = "questionType")
    @Mapping(source = "answers", target = "answers")
    QuestionDto toDto(Question source);
}
