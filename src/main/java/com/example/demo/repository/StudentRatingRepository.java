package com.example.demo.repository;

import com.example.demo.model.Classroom;
import com.example.demo.model.User;
import com.example.demo.model.rating.StudentRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRatingRepository extends JpaRepository<StudentRating, Long> {
    public List<StudentRating> findAllBySubject(Classroom subject);
    public StudentRating findBySubjectAndStudent(Classroom subject, User student);
}
