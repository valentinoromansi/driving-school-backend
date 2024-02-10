package com.drivingschool.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table
public class Question {
    @Id
    private Long id;
    private String ime;
    private Long godine;
    private boolean activated;
    private Date datum4;
}
