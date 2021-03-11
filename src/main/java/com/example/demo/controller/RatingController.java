package com.example.demo.controller;

import com.example.demo.model.Rubric;
import com.example.demo.model.dto.ClassroomRatingDto;
import com.example.demo.model.dto.RubricDto;
import com.example.demo.model.rating.ClassroomRating;
import com.example.demo.model.rating.StudentRating;
import com.example.demo.model.request.ClassroomRatingRequest;
import com.example.demo.model.request.RatingRequest;
import com.example.demo.repository.ClassroomRatingRepository;
import com.example.demo.service.ClassroomRatingService;
import com.example.demo.service.ClassroomService;
import com.example.demo.service.StudentRatingService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RatingController {
    @Autowired
    private StudentRatingService studentRatingService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassroomRatingService classroomRatingService;
    @Autowired
    private ClassroomService classroomService;

    @RequestMapping(value = "/rating/classroom/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClassroomRatingDto> listAll(@PathVariable("id") Long id) {
        ClassroomRating classroomRating = classroomRatingService.findBySubject(classroomService.findById(id));
        if (classroomRating == null) {
            classroomRating = new ClassroomRating(classroomService.findById(id));
            classroomRatingService.save(classroomRating);
        }
        ClassroomRatingDto dto = new ClassroomRatingDto(classroomRating);
        return new ResponseEntity<ClassroomRatingDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating/classroom/{id}/rate", method = RequestMethod.POST)
    public ResponseEntity<ClassroomRatingDto> rate(@PathVariable("id") Long id, @RequestBody RatingRequest ratingRequest) {
        ClassroomRating classroomRating = classroomRatingService.findBySubject(classroomService.findById(id));
        if (classroomRating == null) {
            classroomRating = new ClassroomRating(classroomService.findById(id));
            classroomRatingService.save(classroomRating);
        }
        StudentRating rating = studentRatingService.findBySubjectAndStudent(classroomService.findById(ratingRequest.getSubjectId()), userService.findById(ratingRequest.getStudentId()));
        StudentRating ratingOld = studentRatingService.findBySubjectAndStudent(classroomRating.getSubject(), rating.getStudent());
        if (ratingOld == null) {
            classroomRating.setPoint((classroomRating.getPoint() * classroomRating.getCount() + rating.getPoint()) / (classroomRating.getCount() + 1));
            studentRatingService.save(rating);
            classroomRatingService.save(classroomRating);
        } else {
            classroomRating.setPoint((classroomRating.getPoint() * classroomRating.getCount() + rating.getPoint() - ratingOld.getPoint()) / (classroomRating.getCount()));
            studentRatingService.save(rating);
            classroomRatingService.save(classroomRating);
        }
        ClassroomRatingDto dto = new ClassroomRatingDto(classroomRating);
        return new ResponseEntity<ClassroomRatingDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating/classroom/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomRatingDto> update(@PathVariable("id") Long id, @RequestBody ClassroomRatingRequest request) {
        ClassroomRating classroomRating = classroomRatingService.findBySubject(classroomService.findById(request.getSubjectId()));
        if (classroomRating == null) {
            classroomRating = new ClassroomRating(classroomService.findById(id));
        }
        classroomRatingService.save(classroomRating);
        ClassroomRatingDto dto = new ClassroomRatingDto(classroomRating);
        return new ResponseEntity<ClassroomRatingDto>(dto, HttpStatus.OK);
    }
}
