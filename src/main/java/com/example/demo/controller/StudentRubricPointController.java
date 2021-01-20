package com.example.demo.controller;

import com.example.demo.model.StudentRubricPoint;
import com.example.demo.model.dto.StudentRubricPointDto;
import com.example.demo.service.StudentRubricPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class StudentRubricPointController {
    @Autowired
    public StudentRubricPointService service;

    //Get List StudentRubricPoint
    @RequestMapping(value = "/student-rubric-point", method = RequestMethod.GET)
    public ResponseEntity<List<StudentRubricPointDto>> listAll() {
        List<StudentRubricPoint> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<StudentRubricPointDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<StudentRubricPointDto> dtos = new ArrayList<>();
        for (StudentRubricPoint studentRubricPoint : accounts) {
            StudentRubricPointDto dto = new StudentRubricPointDto(studentRubricPoint);
            dtos.add(dto);
        }
        return new ResponseEntity<List<StudentRubricPointDto>>(dtos, HttpStatus.OK);
    }

    //Get StudentRubricPoint By Id
    @RequestMapping(value = "/student-rubric-point/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentRubricPointDto> getById(@PathVariable("id") Long id) {
        StudentRubricPoint account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<StudentRubricPointDto>(HttpStatus.NOT_FOUND);
        }
        StudentRubricPointDto dto = new StudentRubricPointDto(account);
        return new ResponseEntity<StudentRubricPointDto>(dto, HttpStatus.OK);
    }

    //Create StudentRubricPoint
    @RequestMapping(value = "/student-rubric-point", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody StudentRubricPoint studentRubricPoint, UriComponentsBuilder ucBuilder) {
        service.save(studentRubricPoint);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student-rubric-point/{id}").buildAndExpand(studentRubricPoint.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update StudentRubricPoint
    @RequestMapping(value = "/student-rubric-point/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<StudentRubricPointDto> updateAdmin(@PathVariable("id") Long id, @RequestBody StudentRubricPoint studentRubricPoint) {

        StudentRubricPoint current = service.findById(id);

        if (current == null) {
            return new ResponseEntity<StudentRubricPointDto>(HttpStatus.NOT_FOUND);
        }

        current = studentRubricPoint;

        service.save(current);
        StudentRubricPointDto dto = new StudentRubricPointDto(current);
        return new ResponseEntity<StudentRubricPointDto>(dto, HttpStatus.OK);
    }

    //Delete StudentRubricPoint
    @RequestMapping(value = "/student-rubric-point/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentRubricPoint> delete(@PathVariable("id") Long id) {

        StudentRubricPoint studentRubricPoint = service.findById(id);
        if (studentRubricPoint == null) {
            return new ResponseEntity<StudentRubricPoint>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<StudentRubricPoint>(HttpStatus.NO_CONTENT);
    }
}
