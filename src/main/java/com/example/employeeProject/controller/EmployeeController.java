package com.example.employeeProject.controller;

import com.example.employeeProject.model.request.EmployeeRequest;
import com.example.employeeProject.model.response.CustomResponse;
import com.example.employeeProject.model.response.EntityResponse;
import com.example.employeeProject.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/apiEmployee")
public class
EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?>saveOrUpdateEmployee(@ModelAttribute EmployeeRequest employeeRequest){
        try {
            return new ResponseEntity(iEmployeeService.saveOrUpdateEmployee(employeeRequest), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }
    @GetMapping("/getById")
    public ResponseEntity<?>getByIdEmployee(@RequestParam Long employeeId){
        try {
            return new ResponseEntity(new EntityResponse(iEmployeeService.getByIdEmployee(employeeId),0),HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?>getAllEmployee(){
        try {
            return new ResponseEntity(new EntityResponse(iEmployeeService.getAllEmployee(), 0), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @DeleteMapping("/softDelete")
    public ResponseEntity<?>softDeleteEmployee(@RequestParam Long employeeId){
        try {
            return new ResponseEntity(new EntityResponse(iEmployeeService.softDeleteEmployee(employeeId), 0), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }

    }
    @GetMapping("/getAllPagination")
    public ResponseEntity<?>getPagination(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                          @RequestParam(value = "pageSize",defaultValue = "30",required = false)Integer pageSize){
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(new EntityResponse(iEmployeeService.getAllPaginationEmployee(pageable),0),HttpStatus.OK);
        }catch (Exception e ){
            return  new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PostMapping("/dateTime")
    public ResponseEntity<?>dateTimeEmployee(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                             @RequestParam(value = "pageSize",defaultValue = "20",required = false)Integer pageSize,
                                             @RequestParam(required = false)String startDate,
                                             @RequestParam(required = false)String endDate){
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(new EntityResponse(iEmployeeService.dateTimeEmployee(startDate, endDate, pageable), 0), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PostMapping("/fileUpload")
    public ResponseEntity<?>fileUpload(@ModelAttribute("userFile")  MultipartFile userFile){
        try {
            return new ResponseEntity<>(new EntityResponse(iEmployeeService.fileUpload(userFile), 0), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }

    }
    @GetMapping("/getProjection")
    public ResponseEntity<?>getProjectionEmployee(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        try{
            Pageable pageable=PageRequest.of(Optional.ofNullable(pageNumber).orElse(0),Optional.ofNullable(pageSize).orElse(40));
            return new ResponseEntity(new EntityResponse(iEmployeeService.getProjectionEmployee(pageable),0),HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getSearching")
    public ResponseEntity<?>getSearchingEmployee(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                @RequestParam(value = "pageSize",defaultValue = "30",required = false)Integer pageSize,
                                                @RequestParam(required = false)String mobileNo,
                                                @RequestParam(required = false)String city,
                                                @RequestParam(required = false)String eMail){
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), Optional.ofNullable(pageSize).orElse(30));
            return new ResponseEntity(new EntityResponse(iEmployeeService.getSearchingEmployee(mobileNo, city, eMail, pageable), 0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
}
