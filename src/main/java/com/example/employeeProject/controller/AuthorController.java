package com.example.employeeProject.controller;

import com.example.employeeProject.model.request.AuthorRequest;
import com.example.employeeProject.model.response.EntityResponse;
import com.example.employeeProject.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiAuthor")
public class AuthorController {

    @Autowired
    private IAuthorService iAuthorService;

    @PostMapping("/saveAuthor")
    public ResponseEntity<?> saveAuthor(@RequestBody AuthorRequest authorRequest) {
        try {
            return new ResponseEntity(iAuthorService.saveAuthor(authorRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
}
