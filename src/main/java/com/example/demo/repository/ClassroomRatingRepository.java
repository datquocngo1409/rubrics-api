package com.example.demo.repository;

import com.example.demo.model.Classroom;
import com.example.demo.model.rating.ClassroomRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRatingRepository extends JpaRepository<ClassroomRating, Long> {
    public ClassroomRating findBySubject(Classroom subject);
}
