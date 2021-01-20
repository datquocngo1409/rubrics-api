package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;
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
public class UserController {
    @Autowired
    public UserService userService;

    //Get List User
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllUsers() {
        List<User> accounts = userService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : accounts) {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllStudents() {
        List<User> accounts = userService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : accounts) {
            UserDto userDto = new UserDto(user);
            if (user.getRole().equals("STUDENT")) {
                userDtos.add(userDto);
            }
        }
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllTeachers() {
        List<User> accounts = userService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : accounts) {
            UserDto userDto = new UserDto(user);
            if (user.getRole().equals("TEACHER")) {
                userDtos.add(userDto);
            }
        }
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listAllAdmins() {
        List<User> accounts = userService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<UserDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : accounts) {
            UserDto userDto = new UserDto(user);
            if (user.getRole().equals("ADMIN")) {
                userDtos.add(userDto);
            }
        }
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
    }

    //Get User By Id
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        User account = userService.findById(id);
        if (account == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        UserDto userDto = new UserDto(account);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/getByUsername/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable("username") String username) {
        User account = userService.findByUsername(username);
        if (account == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }
        UserDto userDto = new UserDto(account);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    //Create User
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        userService.updateUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Create Student
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        user.setRole("STUDENT");
        userService.updateUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Create Student
    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    public ResponseEntity<Void> createTeacher(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        user.setRole("TEACHER");
        userService.updateUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/teacher/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Create Student
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<Void> createAdmin(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        user.setRole("ADMIN");
        userService.updateUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/admin/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //Update User
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<UserDto> updateAdmin(@PathVariable("id") Long id, @RequestBody User user) {

        User curremUser = userService.findById(id);

        if (curremUser == null) {
            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
        }

        curremUser = user;

        userService.updateUser(curremUser);
        UserDto userDto = new UserDto(curremUser);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    //Delete User
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {

        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
