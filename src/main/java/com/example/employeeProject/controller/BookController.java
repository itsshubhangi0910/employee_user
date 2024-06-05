package com.example.employeeProject.controller;

import com.example.employeeProject.model.request.BookRequest;
import com.example.employeeProject.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiBook")
public class BookController {
    @Autowired
    private IBookService iBookService;

    @PostMapping("/saveBook")
    public ResponseEntity<?> saveBook(@RequestBody BookRequest bookRequest) {
        try {
            return new ResponseEntity(iBookService.saveBook(bookRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
}
