package com.example.demo.service;

import com.example.demo.model.Classroom;
import com.example.demo.model.TranscriptInfomation;
import com.example.demo.repository.TranscriptInfomationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranscriptInfomationService {
    @Autowired
    private TranscriptInfomationRepository repository;

    public List<TranscriptInfomation> findAll() {
        return repository.findAll();
    }

    public TranscriptInfomation findById(Long id) {
        return repository.findById(id).get();
    }

    public TranscriptInfomation findByClassroom(Classroom classroom) {
        return repository.findByClassroom(classroom);
    }

    public void create(TranscriptInfomation model) {
        repository.save(model);
    }

    public void save(TranscriptInfomation model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
