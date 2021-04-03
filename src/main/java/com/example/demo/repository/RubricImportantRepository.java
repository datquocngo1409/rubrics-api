package com.example.demo.repository;

import com.example.demo.model.Rubric;
import com.example.demo.model.RubricImportant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubricImportantRepository extends JpaRepository<RubricImportant, Long> {
    public RubricImportant findByRubricAndImportant(Rubric rubric, double important);
    public RubricImportant findByRubricAndImportantAndClassroomId(Rubric rubric, double important, Long classroomId);
    public List<RubricImportant> findAllByClassroomId(Long classroomId);
}
