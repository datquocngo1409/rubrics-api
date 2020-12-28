package com.example.demo.service;

import com.example.demo.model.Classroom;
import com.example.demo.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository repository;

    public List<Classroom> findAll() {
        return repository.findAll();
    }

    public Classroom findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(Classroom model) {
        repository.save(model);
    }

    public void save(Classroom model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
