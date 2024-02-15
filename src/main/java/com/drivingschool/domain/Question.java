package com.drivingschool.domain;

import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    //Long question_type_id;
    @ManyToOne
    @JoinColumn(name = "question_type_id")
    QuestionType questionType;
    @ManyToMany
    @JoinTable(name = "map_question__resource",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    @JsonIgnoreProperties("resourceType")
    List<Resource> resources;
}
