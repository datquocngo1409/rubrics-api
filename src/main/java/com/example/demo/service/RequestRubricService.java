package com.example.demo.service;

import com.example.demo.model.RequestRubric;
import com.example.demo.model.Rubric;
import com.example.demo.repository.RequestRubricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestRubricService {
    @Autowired
    private RequestRubricRepository repository;

    public List<RequestRubric> findAll() {
        return repository.findAll();
    }

    public List<RequestRubric> findAllNotCreated() {
        return repository.findAllByCreated(false);
    }

    public List<RequestRubric> findAllCreated() {
        return repository.findAllByCreated(true);
    }

    public RequestRubric findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(RequestRubric model) {
        repository.save(model);
    }

    public void save(RequestRubric model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
