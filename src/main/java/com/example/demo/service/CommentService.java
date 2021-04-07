package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.StudentRubricPoint;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public List<Comment> findBySrp(StudentRubricPoint srp) {
        return repository.findAllBySrp(srp);
    }

    public Comment findById(Long id) {
        return repository.findById(id).get();
    }

    public void create(Comment model) {
        repository.save(model);
    }

    public void save(Comment model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
