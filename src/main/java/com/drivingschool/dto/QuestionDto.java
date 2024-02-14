package com.drivingschool.dto;

import com.drivingschool.domain.Answer;
import com.drivingschool.domain.enumeration.QuestionTypeE;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class QuestionDto {
    Long id;
    String text;
    String explanation;
    QuestionTypeE questionType;
    List<AnswerDto> answers;
    Date last_updated;
}