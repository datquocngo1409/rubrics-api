package com.example.demo.controller;

import com.example.demo.model.StudentTotalRubricPoint;
import com.example.demo.model.dto.StudentTotalRubricPointDto;
import com.example.demo.service.StudentTotalRubricPointService;
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
public class StudentTotalRubricPointController {
    @Autowired
    public StudentTotalRubricPointService service;

    //Get List StudentTotalRubricPoint
    @RequestMapping(value = "/student-total-rubric-point", method = RequestMethod.GET)
    public ResponseEntity<List<StudentTotalRubricPointDto>> listAll() {
        List<StudentTotalRubricPoint> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<StudentTotalRubricPointDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<StudentTotalRubricPointDto> dtos = new ArrayList<>();
        for (StudentTotalRubricPoint studentTotalRubricPoint : accounts) {
            StudentTotalRubricPointDto dto = new StudentTotalRubricPointDto(studentTotalRubricPoint);
            dtos.add(dto);
        }
        return new ResponseEntity<List<StudentTotalRubricPointDto>>(dtos, HttpStatus.OK);
    }

    //Get StudentTotalRubricPoint By Id
    @RequestMapping(value = "/student-total-rubric-point/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentTotalRubricPointDto> getById(@PathVariable("id") Long id) {
        System.out.println("Fetching StudentTotalRubricPoint with id " + id);
        StudentTotalRubricPoint account = service.findById(id);
        if (account == null) {
            System.out.println("StudentTotalRubricPoint with id " + id + " not found");
            return new ResponseEntity<StudentTotalRubricPointDto>(HttpStatus.NOT_FOUND);
        }
        StudentTotalRubricPointDto dto = new StudentTotalRubricPointDto(account);
        return new ResponseEntity<StudentTotalRubricPointDto>(dto, HttpStatus.OK);
    }

    //Create StudentTotalRubricPoint
    @RequestMapping(value = "/student-total-rubric-point", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody StudentTotalRubricPoint studentTotalRubricPoint, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating StudentTotalRubricPoint " + studentTotalRubricPoint.getId());
        service.save(studentTotalRubricPoint);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student-total-rubric-point/{id}").buildAndExpand(studentTotalRubricPoint.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update StudentTotalRubricPoint
    @RequestMapping(value = "/student-total-rubric-point/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<StudentTotalRubricPointDto> updateAdmin(@PathVariable("id") Long id, @RequestBody StudentTotalRubricPoint studentTotalRubricPoint) {
        System.out.println("Updating StudentTotalRubricPoint " + id);

        StudentTotalRubricPoint current = service.findById(id);

        if (current == null) {
            System.out.println("StudentTotalRubricPoint with id " + id + " not found");
            return new ResponseEntity<StudentTotalRubricPointDto>(HttpStatus.NOT_FOUND);
        }

        current = studentTotalRubricPoint;

        service.save(current);
        StudentTotalRubricPointDto dto = new StudentTotalRubricPointDto(current);
        return new ResponseEntity<StudentTotalRubricPointDto>(dto, HttpStatus.OK);
    }

    //Delete StudentTotalRubricPoint
    @RequestMapping(value = "/student-total-rubric-point/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StudentTotalRubricPoint> delete(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting StudentTotalRubricPoint with id " + id);

        StudentTotalRubricPoint studentTotalRubricPoint = service.findById(id);
        if (studentTotalRubricPoint == null) {
            System.out.println("Unable to delete. StudentTotalRubricPoint with id " + id + " not found");
            return new ResponseEntity<StudentTotalRubricPoint>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<StudentTotalRubricPoint>(HttpStatus.NO_CONTENT);
    }
}
