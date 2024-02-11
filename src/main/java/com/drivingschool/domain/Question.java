package com.drivingschool.domain;

import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table
@Data
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    @NotNull
    @Column(unique=true)
    String text;
    String explanation;
    //Long question_type_id;
    @ManyToOne
    @JoinColumn(name = "question_type_id")
    QuestionType questionType;
    @JsonIgnore
    @UpdateTimestamp
    Date last_updated;
}
