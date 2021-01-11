package com.example.demo.service;

import com.example.demo.model.Notification;
import com.example.demo.model.Rubric;
import com.example.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repository;

    public List<Notification> findAll() {
        return repository.findAll();
    }

    public List<Notification> findAllByTo(String to) {
        return repository.findAllByToUser(to);
    }

    public Notification findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(Notification model) {
        repository.save(model);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
