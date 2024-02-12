package com.drivingschool.dto;

import com.drivingschool.domain.QuestionType;
import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Builder
public class QuestionDTO {
    Long id;
    String text;
    String explanation;
    QuestionTypeE questionType;
}
