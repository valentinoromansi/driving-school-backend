package com.drivingschool.domain;

import com.drivingschool.domain.enumeration.QuestionTypeE;
import com.drivingschool.domain.enumeration.ResourceTypeE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceType {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private ResourceTypeE id;
}
