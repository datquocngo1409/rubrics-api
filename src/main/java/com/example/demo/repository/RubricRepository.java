package com.example.demo.repository;

import com.example.demo.model.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubricRepository extends JpaRepository<Rubric, Long> {
    public List<Rubric> findAllByName(String name);
}
