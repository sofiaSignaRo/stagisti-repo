package com.ProjectAPI.APIRestUsers.controller;

import com.ProjectAPI.APIRestUsers.entity.ErrorHandler;
import com.ProjectAPI.APIRestUsers.entity.User;
import com.ProjectAPI.APIRestUsers.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/userlist")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserREST {
    @Autowired
    private UserService service;

    @Value("${custom.message}")
    private String customMessage;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = service.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id){
        try {
            User foundUser = service.getUser(id);
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }catch (Exception e){
            ErrorHandler thisError = new ErrorHandler();
            thisError.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            thisError.setErrorMessage(e.getMessage() + customMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(thisError);
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = service.getAllUser();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        User updatedUser = service.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
        return ResponseEntity.ok("The User have been correctly eliminated!");
    }
}

//DTO e gestione exc
//creare oggetto info e nel body passare err

/*
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id){
        try {
            User foundUser = service.getUser(id);
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }catch (Exception e){
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage() + customMessage);
            errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

    }
 */