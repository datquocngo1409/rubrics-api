package com.example.demo.controller;

import com.example.demo.model.RubricImportant;
import com.example.demo.model.dto.RubricImportantDto;
import com.example.demo.service.RubricImportantService;
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
public class RubricImportantController {
    @Autowired
    public RubricImportantService service;

    //Get List RubricImportant
    @RequestMapping(value = "/rubric-important", method = RequestMethod.GET)
    public ResponseEntity<List<RubricImportantDto>> listAll() {
        List<RubricImportant> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<RubricImportantDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RubricImportantDto> dtos = new ArrayList<>();
        for (RubricImportant rubricImportant : accounts) {
            RubricImportantDto dto = new RubricImportantDto(rubricImportant);
            dtos.add(dto);
        }
        return new ResponseEntity<List<RubricImportantDto>>(dtos, HttpStatus.OK);
    }

    //Get Classroom By Id
    @RequestMapping(value = "/rubric-important/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RubricImportantDto> getById(@PathVariable("id") Long id) {
        RubricImportant account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<RubricImportantDto>(HttpStatus.NOT_FOUND);
        }
        RubricImportantDto dto = new RubricImportantDto(account);
        return new ResponseEntity<RubricImportantDto>(dto, HttpStatus.OK);
    }

    //Create RubricImportant
    @RequestMapping(value = "/rubric-important", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody RubricImportant rubricImportant, UriComponentsBuilder ucBuilder) {
        service.save(rubricImportant);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rubric-important/{id}").buildAndExpand(rubricImportant.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update RubricImportant
    @RequestMapping(value = "/rubric-important/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<RubricImportantDto> updateAdmin(@PathVariable("id") Long id, @RequestBody RubricImportant rubricImportant) {

        RubricImportant current = service.findById(id);

        if (current == null) {
            return new ResponseEntity<RubricImportantDto>(HttpStatus.NOT_FOUND);
        }

        current = rubricImportant;

        service.save(current);
        RubricImportantDto dto = new RubricImportantDto(current);
        return new ResponseEntity<RubricImportantDto>(dto, HttpStatus.OK);
    }

    //Delete RubricImportant
    @RequestMapping(value = "/rubric-important/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RubricImportant> delete(@PathVariable("id") Long id) {

        RubricImportant rubricImportant = service.findById(id);
        if (rubricImportant == null) {
            return new ResponseEntity<RubricImportant>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<RubricImportant>(HttpStatus.NO_CONTENT);
    }
}
