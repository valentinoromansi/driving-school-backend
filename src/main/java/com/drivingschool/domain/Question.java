package com.drivingschool.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
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
    String text;
    String explanation;
    //Long question_type_id;
    @UpdateTimestamp
    Date last_updated;
}
