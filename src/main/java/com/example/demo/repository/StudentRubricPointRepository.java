package com.example.demo.repository;

import com.example.demo.model.RubricImportant;
import com.example.demo.model.StudentRubricPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRubricPointRepository extends JpaRepository<StudentRubricPoint, Long> {
    List<StudentRubricPoint> findAllByRubricImportant(RubricImportant rubricImportant);
}
