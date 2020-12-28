package com.example.demo.service;

import com.example.demo.model.Rubric;
import com.example.demo.repository.RubricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubricService {
    @Autowired
    private RubricRepository repository;

    public List<Rubric> findAll() {
        return repository.findAll();
    }

    public Rubric findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(Rubric model) {
        repository.save(model);
    }

    public void save(Rubric model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
