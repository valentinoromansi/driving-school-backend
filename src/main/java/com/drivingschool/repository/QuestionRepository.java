package com.drivingschool.repository;

import com.drivingschool.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    public List<Question> findAllByTextContaining(String textSubstring);

    @Query("select q from Question q where text like %:textSubstring%")
    public List<Question> findAllByTextContaining_query(@Param("textSubstring") String textSubstring);
}
