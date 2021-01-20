package com.example.demo.controller;

import com.example.demo.model.TranscriptInfomation;
import com.example.demo.model.dto.TranscriptInfomationDto;
import com.example.demo.service.TranscriptInfomationService;
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
public class TranscriptInfomationController {
    @Autowired
    public TranscriptInfomationService service;

    //Get List TranscriptInfomation
    @RequestMapping(value = "/transcript-infomation", method = RequestMethod.GET)
    public ResponseEntity<List<TranscriptInfomationDto>> listAll() {
        List<TranscriptInfomation> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<TranscriptInfomationDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<TranscriptInfomationDto> dtos = new ArrayList<>();
        for (TranscriptInfomation transcriptInfomation : accounts) {
            TranscriptInfomationDto dto = new TranscriptInfomationDto(transcriptInfomation);
            dtos.add(dto);
        }
        return new ResponseEntity<List<TranscriptInfomationDto>>(dtos, HttpStatus.OK);
    }

    //Get TranscriptInfomation By Id
    @RequestMapping(value = "/transcript-infomation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TranscriptInfomationDto> getById(@PathVariable("id") Long id) {
        TranscriptInfomation account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<TranscriptInfomationDto>(HttpStatus.NOT_FOUND);
        }
        TranscriptInfomationDto dto = new TranscriptInfomationDto(account);
        return new ResponseEntity<TranscriptInfomationDto>(dto, HttpStatus.OK);
    }

    //Create TranscriptInfomation
    @RequestMapping(value = "/transcript-infomation", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody TranscriptInfomation transcriptInfomation, UriComponentsBuilder ucBuilder) {
        service.save(transcriptInfomation);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/transcript-infomation/{id}").buildAndExpand(transcriptInfomation.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update TranscriptInfomation
    @RequestMapping(value = "/transcript-infomation/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<TranscriptInfomationDto> updateAdmin(@PathVariable("id") Long id, @RequestBody TranscriptInfomation transcriptInfomation) {

        TranscriptInfomation current = service.findById(id);

        if (current == null) {
            return new ResponseEntity<TranscriptInfomationDto>(HttpStatus.NOT_FOUND);
        }

        current = transcriptInfomation;

        service.save(current);
        TranscriptInfomationDto dto = new TranscriptInfomationDto(current);
        return new ResponseEntity<TranscriptInfomationDto>(dto, HttpStatus.OK);
    }

    //Delete TranscriptInfomation
    @RequestMapping(value = "/transcript-infomation/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TranscriptInfomation> delete(@PathVariable("id") Long id) {

        TranscriptInfomation transcriptInfomation = service.findById(id);
        if (transcriptInfomation == null) {
            return new ResponseEntity<TranscriptInfomation>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<TranscriptInfomation>(HttpStatus.NO_CONTENT);
    }
}
