package com.example.demo.repository;

import com.example.demo.model.Classroom;
import com.example.demo.model.TranscriptInfomation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptInfomationRepository extends JpaRepository<TranscriptInfomation, Long> {
    public TranscriptInfomation findByClassroom(Classroom classroom);
}
