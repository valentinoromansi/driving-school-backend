package com.drivingschool.repository;

import com.drivingschool.domain.ResourceType;
import com.drivingschool.domain.enumeration.ResourceTypeE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceTypeRepository extends JpaRepository<ResourceType, ResourceTypeE> {
}
