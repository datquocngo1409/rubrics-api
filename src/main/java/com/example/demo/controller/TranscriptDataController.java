package com.example.demo.controller;

import com.example.demo.model.TranscriptData;
import com.example.demo.model.dto.TranscriptDataDto;
import com.example.demo.service.TranscriptDataService;
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
public class TranscriptDataController {
    @Autowired
    public TranscriptDataService service;

    //Get List TranscriptData
    @RequestMapping(value = "/transcript-data", method = RequestMethod.GET)
    public ResponseEntity<List<TranscriptDataDto>> listAll() {
        List<TranscriptData> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<TranscriptDataDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<TranscriptDataDto> dtos = new ArrayList<>();
        for (TranscriptData transcriptData : accounts) {
            TranscriptDataDto dto = new TranscriptDataDto(transcriptData);
            dtos.add(dto);
        }
        return new ResponseEntity<List<TranscriptDataDto>>(dtos, HttpStatus.OK);
    }

    //Get TranscriptData By Id
    @RequestMapping(value = "/transcript-data/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TranscriptDataDto> getById(@PathVariable("id") Long id) {
        TranscriptData account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<TranscriptDataDto>(HttpStatus.NOT_FOUND);
        }
        TranscriptDataDto dto = new TranscriptDataDto(account);
        return new ResponseEntity<TranscriptDataDto>(dto, HttpStatus.OK);
    }

    //Create TranscriptData
    @RequestMapping(value = "/transcript-data", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody TranscriptData transcriptData, UriComponentsBuilder ucBuilder) {
        service.save(transcriptData);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/transcript-data/{id}").buildAndExpand(transcriptData.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update TranscriptData
    @RequestMapping(value = "/transcript-data/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<TranscriptDataDto> updateAdmin(@PathVariable("id") Long id, @RequestBody TranscriptData transcriptData) {

        TranscriptData current = service.findById(id);

        if (current == null) {
            return new ResponseEntity<TranscriptDataDto>(HttpStatus.NOT_FOUND);
        }

        current = transcriptData;

        service.save(current);
        TranscriptDataDto dto = new TranscriptDataDto(current);
        return new ResponseEntity<TranscriptDataDto>(dto, HttpStatus.OK);
    }

    //Delete TranscriptData
    @RequestMapping(value = "/transcript-data/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TranscriptData> delete(@PathVariable("id") Long id) {

        TranscriptData transcriptData = service.findById(id);
        if (transcriptData == null) {
            return new ResponseEntity<TranscriptData>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<TranscriptData>(HttpStatus.NO_CONTENT);
    }
}
