package com.example.demo.repository;

import com.example.demo.model.StudentTotalRubricPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTotalRubricPointRepository extends JpaRepository<StudentTotalRubricPoint, Long> {
}
