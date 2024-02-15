package com.drivingschool.dto;

import com.drivingschool.domain.ResourceType;
import com.drivingschool.domain.enumeration.ResourceTypeE;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceDto {
    Long id;
    String uri;
    ResourceTypeE resourceType;
}
