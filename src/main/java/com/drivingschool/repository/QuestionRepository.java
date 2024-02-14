package com.drivingschool.repository;

import com.drivingschool.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findOneById(Long id);
    Optional<Question> findOneByText(String text);
    Page<Question> findAllByTextContaining(String textSubstring, Pageable pageable);

    @Query("select q from Question q where text like %:textSubstring%")
    List<Question> findAllByTextContaining_query(@Param("textSubstring") String textSubstring);
}
