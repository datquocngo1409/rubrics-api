package com.example.demo.service;

import com.example.demo.model.StudentRubricPoint;
import com.example.demo.repository.StudentRubricPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRubricPointService {
    @Autowired
    private StudentRubricPointRepository repository;

    public List<StudentRubricPoint> findAll() {
        return repository.findAll();
    }

    public StudentRubricPoint findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(StudentRubricPoint model) {
        repository.save(model);
    }

    public void save(StudentRubricPoint model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
