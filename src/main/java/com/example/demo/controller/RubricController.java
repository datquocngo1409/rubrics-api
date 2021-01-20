package com.example.demo.controller;

import com.example.demo.model.Rubric;
import com.example.demo.model.dto.RubricDto;
import com.example.demo.service.RubricService;
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
public class RubricController {
    @Autowired
    public RubricService service;

    //Get List Rubric
    @RequestMapping(value = "/rubric", method = RequestMethod.GET)
    public ResponseEntity<List<RubricDto>> listAll() {
        List<Rubric> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<RubricDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RubricDto> dtos = new ArrayList<>();
        for (Rubric rubric : accounts) {
            RubricDto dto = new RubricDto(rubric);
            dtos.add(dto);
        }
        return new ResponseEntity<List<RubricDto>>(dtos, HttpStatus.OK);
    }

    //Get Rubric By Id
    @RequestMapping(value = "/rubric/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RubricDto> getById(@PathVariable("id") Long id) {
        Rubric account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<RubricDto>(HttpStatus.NOT_FOUND);
        }
        RubricDto dto = new RubricDto(account);
        return new ResponseEntity<RubricDto>(dto, HttpStatus.OK);
    }

    //Create Rubric
    @RequestMapping(value = "/rubric", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Rubric rubric, UriComponentsBuilder ucBuilder) {
        List<Rubric> list = service.findAllByName(rubric.getName());
        if (list.size() > 0) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        service.save(rubric);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rubric/{id}").buildAndExpand(rubric.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update Rubric
    @RequestMapping(value = "/rubric/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<RubricDto> updateAdmin(@PathVariable("id") Long id, @RequestBody Rubric rubric) {

        Rubric current = service.findById(id);

        if (current == null) {
            return new ResponseEntity<RubricDto>(HttpStatus.NOT_FOUND);
        }

        current = rubric;

        service.save(current);
        RubricDto dto = new RubricDto(current);
        return new ResponseEntity<RubricDto>(dto, HttpStatus.OK);
    }

    //Delete Rubric
    @RequestMapping(value = "/rubric/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Rubric> delete(@PathVariable("id") Long id) {

        Rubric rubric = service.findById(id);
        if (rubric == null) {
            return new ResponseEntity<Rubric>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<Rubric>(HttpStatus.NO_CONTENT);
    }
}
