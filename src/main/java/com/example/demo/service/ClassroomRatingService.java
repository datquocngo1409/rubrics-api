package com.example.demo.service;

import com.example.demo.model.Classroom;
import com.example.demo.model.rating.ClassroomRating;
import com.example.demo.model.rating.StudentRating;
import com.example.demo.repository.ClassroomRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomRatingService {
    @Autowired
    ClassroomRatingRepository repository;

    public List<ClassroomRating> findAll() {
        return repository.findAll();
    }

    public ClassroomRating findBySubject(Classroom classroom) {
        return repository.findBySubject(classroom);
    }

    public ClassroomRating findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(ClassroomRating model) {
        repository.save(model);
    }

    public void save(ClassroomRating model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
