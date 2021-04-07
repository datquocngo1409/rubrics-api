package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.RequestRubric;
import com.example.demo.model.Rubric;
import com.example.demo.model.dto.CommentDto;
import com.example.demo.model.dto.RequestRubricDto;
import com.example.demo.service.CommentService;
import com.example.demo.service.StudentRubricPointService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    public CommentService service;
    @Autowired
    public UserService userService;
    @Autowired
    public StudentRubricPointService srpService;

    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public ResponseEntity<List<CommentDto>> listAll() {
        List<Comment> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<CommentDto>>(HttpStatus.NO_CONTENT);
        }
        List<CommentDto> dtos = new ArrayList<>();
        for (Comment comment : accounts) {
            CommentDto dto = new CommentDto(comment);
            dtos.add(dto);
        }
        return new ResponseEntity<List<CommentDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/bySrp/{srpId}", method = RequestMethod.GET)
    public ResponseEntity<List<CommentDto>> listAllBySrp(@PathVariable("srpId") Long srpId) {
        List<Comment> accounts = service.findBySrp(srpService.findById(srpId));
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<CommentDto>>(HttpStatus.NO_CONTENT);
        }
        List<CommentDto> dtos = new ArrayList<>();
        for (Comment comment : accounts) {
            CommentDto dto = new CommentDto(comment);
            dtos.add(dto);
        }
        return new ResponseEntity<List<CommentDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDto> getById(@PathVariable("id") Long id) {
        Comment account = service.findById(id);
        if (account == null) {
            return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
        }
        CommentDto dto = new CommentDto(account);
        return new ResponseEntity<CommentDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody CommentDto commentDto, UriComponentsBuilder ucBuilder) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setCreationTime(new Date());
        comment.setUserComment(userService.findByUsername(commentDto.getUserCommentUsername()));
        comment.setSrp(srpService.findById(commentDto.getSrpId()));
        service.save(comment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comment/{id}").buildAndExpand(comment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> delete(@PathVariable("id") Long id) {
        Comment comment = service.findById(id);
        if (comment == null) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
    }
}
