package com.example.demo.controller;

import com.example.demo.model.RequestRubric;
import com.example.demo.model.Rubric;
import com.example.demo.model.dto.RequestRubricDto;
import com.example.demo.model.dto.RubricDto;
import com.example.demo.service.RequestRubricService;
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
public class RequestRubricController {

    @Autowired
    public RequestRubricService service;
    @Autowired
    public RubricService rubricService;

    @RequestMapping(value = "/request-rubric", method = RequestMethod.GET)
    public ResponseEntity<List<RequestRubricDto>> listAll() {
        List<RequestRubric> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<RequestRubricDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RequestRubricDto> dtos = new ArrayList<>();
        for (RequestRubric requestRubric : accounts) {
            RequestRubricDto dto = new RequestRubricDto(requestRubric);
            dtos.add(dto);
        }
        return new ResponseEntity<List<RequestRubricDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/request-rubric/created", method = RequestMethod.GET)
    public ResponseEntity<List<RequestRubricDto>> listAllCreated() {
        List<RequestRubric> accounts = service.findAllCreated();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<RequestRubricDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RequestRubricDto> dtos = new ArrayList<>();
        for (RequestRubric requestRubric : accounts) {
            RequestRubricDto dto = new RequestRubricDto(requestRubric);
            dtos.add(dto);
        }
        return new ResponseEntity<List<RequestRubricDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/request-rubric/not-created", method = RequestMethod.GET)
    public ResponseEntity<List<RequestRubricDto>> listAllNotCreated() {
        List<RequestRubric> accounts = service.findAllNotCreated();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<RequestRubricDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<RequestRubricDto> dtos = new ArrayList<>();
        for (RequestRubric requestRubric : accounts) {
            RequestRubricDto dto = new RequestRubricDto(requestRubric);
            dtos.add(dto);
        }
        return new ResponseEntity<List<RequestRubricDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/request-rubric/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestRubricDto> getById(@PathVariable("id") Long id) {
        RequestRubric account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<RequestRubricDto>(HttpStatus.NOT_FOUND);
        }
        RequestRubricDto dto = new RequestRubricDto(account);
        return new ResponseEntity<RequestRubricDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/request-rubric", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody RequestRubric requestRubric, UriComponentsBuilder ucBuilder) {
        List<Rubric> list = rubricService.findAllByName(requestRubric.getName());
        if (list.size() > 0) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        service.save(requestRubric);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/request-rubric/{id}").buildAndExpand(requestRubric.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/request-rubric/{id}/accept", method = RequestMethod.POST)
    public ResponseEntity<RubricDto> accept(@PathVariable("id") Long id) {
        RequestRubric requestRubric = service.findById(id);
        Rubric rubric = new Rubric(requestRubric.getName());
        List<Rubric> list = rubricService.findAllByName(rubric.getName());
        if (list.size() > 0) {
            return new ResponseEntity<RubricDto>(HttpStatus.CONFLICT);
        }
        rubricService.save(rubric);
        requestRubric.setCreated(true);
        service.save(requestRubric);
        return new ResponseEntity<RubricDto>(new RubricDto(rubric), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/request-rubric/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Rubric> delete(@PathVariable("id") Long id) {
        RequestRubric rubric = service.findById(id);
        if (rubric == null) {
            return new ResponseEntity<Rubric>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<Rubric>(HttpStatus.NO_CONTENT);
    }
}
