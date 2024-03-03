package com.drivingschool.domain;

import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Table(name = "question")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(unique=true)
    String text;

    String explanation;

    @ManyToOne
    @JoinColumn(name = "question_type_id", nullable = false)
    QuestionType questionType;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL
    )
    @JsonIgnoreProperties(value = "question")
    List<Answer> answers;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "map_question__resource",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    @JsonIgnoreProperties("resourceType")
    List<Resource> resources;


    public Question addAnswers(List<Answer> answers) {
        for (var answer : answers)
            answer.setQuestion(this);
        this.answers = answers;
        return this;
    }

    public Question addResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }

}
