package com.drivingschool.domain;

import com.drivingschool.domain.enumeration.QuestionTypeE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionType {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private QuestionTypeE id;
}
