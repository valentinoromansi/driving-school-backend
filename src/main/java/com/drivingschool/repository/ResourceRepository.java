package com.drivingschool.repository;

import com.drivingschool.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Optional<Resource> findOneById(Long id);
    Optional<Resource> findOneByUri(String uri);
}
