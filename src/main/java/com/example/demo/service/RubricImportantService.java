package com.example.demo.service;

import com.example.demo.model.Rubric;
import com.example.demo.model.RubricImportant;
import com.example.demo.repository.RubricImportantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubricImportantService {
    @Autowired
    private RubricImportantRepository repository;

    public List<RubricImportant> findAll() {
        return repository.findAll();
    }

    public RubricImportant findById(Long id) {
        return repository.findById(id).get();
    }

    public RubricImportant findByRubricAndImportant(Rubric rubric, double important) {
        return repository.findByRubricAndImportant(rubric, important);
    }

    public void create(RubricImportant model) {
        repository.save(model);
    }

    public void save(RubricImportant model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
