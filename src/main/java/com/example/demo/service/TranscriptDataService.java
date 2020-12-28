package com.example.demo.service;

import com.example.demo.model.Classroom;
import com.example.demo.model.TranscriptData;
import com.example.demo.repository.TranscriptDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranscriptDataService {
    @Autowired
    private TranscriptDataRepository repository;

    public List<TranscriptData> findAll() {
        return repository.findAll();
    }

    public TranscriptData findById(Long id) {
        return repository.findById(id).get();
    }

    public TranscriptData findByClassroom(Classroom classroom) {
        return repository.findByClassroom(classroom);
    }

    public void create(TranscriptData model) {
        repository.save(model);
    }

    public void save(TranscriptData model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
