package com.example.demo.service;

import com.example.demo.model.Classroom;
import com.example.demo.model.User;
import com.example.demo.model.rating.StudentRating;
import com.example.demo.repository.StudentRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRatingService {
    @Autowired
    private StudentRatingRepository repository;

    public List<StudentRating> findAll() {
        return repository.findAll();
    }

    public List<StudentRating> findAllBySubject(Classroom classroom) {
        return repository.findAllBySubject(classroom);
    }

    public StudentRating findBySubjectAndStudent(Classroom classroom, User student) { return repository.findBySubjectAndStudent(classroom, student);}

    public StudentRating findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(StudentRating model) {
        repository.save(model);
    }

    public void save(StudentRating model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
