package com.drivingschool.mapper;

import com.drivingschool.domain.Question;
import com.drivingschool.domain.Resource;
import com.drivingschool.dto.QuestionDto;
import com.drivingschool.dto.ResourceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceMapper {
    @Mapping(source = "resourceType", target = "resourceType.id")
    Resource toEntity(ResourceDto source);
    @Mapping(source = "resourceType.id", target = "resourceType")
    ResourceDto toDto(Resource source);
}
