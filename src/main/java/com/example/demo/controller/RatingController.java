package com.example.demo.controller;

import com.example.demo.model.Rubric;
import com.example.demo.model.dto.ClassroomRatingDto;
import com.example.demo.model.dto.RubricDto;
import com.example.demo.model.dto.StudentRatingDto;
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

    @RequestMapping(value = "/rating/classroom/{id}/getRating", method = RequestMethod.GET)
    public ResponseEntity<List<StudentRatingDto>> listRating(@PathVariable("id") Long id) {
        List<StudentRating> studentRatingList = studentRatingService.findAllBySubject(classroomService.findById(id));
        if (studentRatingList.isEmpty()) {
            return new ResponseEntity<List<StudentRatingDto>>(HttpStatus.NO_CONTENT);
        }
        List<StudentRatingDto> dtos = new ArrayList<>();
        for (StudentRating sr : studentRatingList) {
            StudentRatingDto dto = new StudentRatingDto(sr);
            dtos.add(dto);
        }
        return new ResponseEntity<List<StudentRatingDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating/classroom/{id}/rate", method = RequestMethod.POST)
    public ResponseEntity<ClassroomRatingDto> rate(@PathVariable("id") Long id, @RequestBody RatingRequest ratingRequest) {
        ClassroomRating classroomRating = classroomRatingService.findBySubject(classroomService.findById(id));
        if (classroomRating == null) {
            classroomRating = new ClassroomRating(classroomService.findById(id));
            classroomRatingService.save(classroomRating);
        }
        StudentRating rating = new StudentRating(userService.findById(ratingRequest.getStudentId()), classroomService.findById(ratingRequest.getSubjectId()), ratingRequest.getContent(), ratingRequest.getPoint());
        StudentRating ratingOld = studentRatingService.findBySubjectAndStudent(classroomRating.getSubject(), rating.getStudent());
        if (ratingOld == null) {
            classroomRating.setPoint((classroomRating.getPoint() * classroomRating.getCount() + rating.getPoint()) / (classroomRating.getCount() + 1));
            classroomRating.setCount(classroomRating.getCount() + 1);
            studentRatingService.save(rating);
            classroomRatingService.save(classroomRating);
        } else {
            classroomRating.setPoint((classroomRating.getPoint() * classroomRating.getCount() + rating.getPoint() - ratingOld.getPoint()) / (classroomRating.getCount()));
            ratingOld.setPoint(ratingRequest.getPoint());
            ratingOld.setContent(ratingRequest.getContent());
            studentRatingService.save(ratingOld);
            classroomRatingService.save(classroomRating);
        }
        ClassroomRatingDto dto = new ClassroomRatingDto(classroomRating);
        return new ResponseEntity<ClassroomRatingDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/rating/classroom/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<ClassroomRatingDto> update(@PathVariable("id") Long id, @RequestBody ClassroomRatingRequest request) {
        ClassroomRating classroomRating = classroomRatingService.findBySubject(classroomService.findById(request.getSubjectId()));
        classroomRating.setPoint(request.getPoint());
        classroomRating.setCount(request.getCount());
        if (classroomRating == null) {
            classroomRating = new ClassroomRating(classroomService.findById(id));
        }
        classroomRatingService.save(classroomRating);
        ClassroomRatingDto dto = new ClassroomRatingDto(classroomRating);
        return new ResponseEntity<ClassroomRatingDto>(dto, HttpStatus.OK);
    }
}
