package com.example.employeeProject.controller;

import com.example.employeeProject.model.Student;
import com.example.employeeProject.model.response.CustomResponse;
import com.example.employeeProject.model.response.EntityResponse;
import com.example.employeeProject.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiStudent")
public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @PostMapping("/saveOrUpdateStudent")
    public ResponseEntity<?>saveOrUpdateStudent(@RequestBody Student student){
        try{
            return new ResponseEntity(new EntityResponse(iStudentService.saveOrUpdateStudent(student),0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }



}
