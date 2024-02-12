package com.drivingschool.mapper;

import com.drivingschool.domain.Question;
import com.drivingschool.dto.QuestionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "questionType", target = "questionType.id")
    Question toEntity(QuestionDTO source);
    @Mapping(source = "questionType.id", target = "questionType")
    QuestionDTO toDto(Question source);
}
