package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.model.Rubric;
import com.example.demo.model.dto.RubricDto;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class NotificationController {
    @Autowired
    private NotificationService service;

    //Get List Notification
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public ResponseEntity<List<Notification>> listAll() {
        List<Notification> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<Notification>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Notification>>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/notification/count/{user}", method = RequestMethod.GET)
    public ResponseEntity<Long> getCount(@PathVariable("user") String user) {
        List<Notification> accounts = service.findAllByTo(user);
        if (accounts.isEmpty()) {
            return new ResponseEntity<Long>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Long>(Long.valueOf(accounts.size()), HttpStatus.OK);
    }

    @RequestMapping(value = "/notification/{user}", method = RequestMethod.GET)
    public ResponseEntity<List<Notification>> getByUsername(@PathVariable("user") String user) {
        List<Notification> accounts = service.findAllByTo(user);
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<Notification>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Notification>>(accounts, HttpStatus.OK);
    }

    //Get Notification By Id
    @RequestMapping(value = "/notification/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> getById(@PathVariable("id") Long id) {
        System.out.println("Fetching Notification with id " + id);
        Notification account = service.findById(id);
        if (account == null) {
            System.out.println("Notification with id " + id + " not found");
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Notification>(account, HttpStatus.OK);
    }

    //Create Notification
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Notification notification, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Notification " + notification.getToUser());
        service.save(notification);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/notification/{id}").buildAndExpand(notification.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update Notification
    @RequestMapping(value = "/notification/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Notification> updateAdmin(@PathVariable("id") Long id, @RequestBody Notification notification) {
        System.out.println("Updating Notification " + id);

        Notification current = service.findById(id);

        if (current == null) {
            System.out.println("Notification with id " + id + " not found");
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }

        current = notification;

        service.save(current);
        return new ResponseEntity<Notification>(current, HttpStatus.OK);
    }

    //Delete Notification
    @RequestMapping(value = "/notification/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Notification> delete(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Notification with id " + id);

        Notification notification = service.findById(id);
        if (notification == null) {
            System.out.println("Unable to delete. Notification with id " + id + " not found");
            return new ResponseEntity<Notification>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<Notification>(HttpStatus.NO_CONTENT);
    }
}
