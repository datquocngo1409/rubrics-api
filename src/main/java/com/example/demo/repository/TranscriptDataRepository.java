package com.example.demo.repository;

import com.example.demo.model.Classroom;
import com.example.demo.model.TranscriptData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranscriptDataRepository extends JpaRepository<TranscriptData, Long> {
    public TranscriptData findByClassroom(Classroom classroom);
}
