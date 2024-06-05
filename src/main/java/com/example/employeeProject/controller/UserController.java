package com.example.employeeProject.controller;

import com.example.employeeProject.model.request.UserRequest;
import com.example.employeeProject.model.response.CustomResponse;
import com.example.employeeProject.model.response.EntityResponse;
import com.example.employeeProject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/apiUser")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/userLogin")
    public ResponseEntity<?>userLoginUser(@RequestBody UserRequest userRequest){
        try {
            return new ResponseEntity(iUserService.userLoginUser(userRequest),HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
    @PostMapping("/changePassword")
    public ResponseEntity<?>changePasswordUser(@RequestParam Long userId,
                                               @RequestParam  String newPassword,
                                               @RequestParam  String oldPassword){
        try {
            return new ResponseEntity(iUserService.changePasswordUser( userId, newPassword,oldPassword),HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }
    @GetMapping("/getAllPagination")
    public  ResponseEntity<?>getAllPaginationUser(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                  @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize){
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(new EntityResponse(iUserService.getAllPaginationUser(pageable), 0), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);

        }
    }

    @PostMapping("/startDateEndDate")
    public ResponseEntity<?>startDateEndDateUser(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                                 @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                                 @RequestParam(required = false)String startDate,
                                                 @RequestParam(required = false)String endDate){
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(new EntityResponse(iUserService.startDateEndDateUser(startDate,endDate,pageable),0),HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);

        }
    }
    @GetMapping("/getById")
    public ResponseEntity<?>getByIdUser(@RequestParam Long userId){
        try {
            return new ResponseEntity(new EntityResponse(iUserService.getByIdUser(userId), 0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }

    }
    @GetMapping("/getSearching")
    public ResponseEntity<?>getSearchingUser(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                             @RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
                                             @RequestParam(required = false)String search){
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), Optional.ofNullable(pageSize).orElse(10));
            return new ResponseEntity(new EntityResponse(iUserService.getSearchingUser(search,pageable), 0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
//    @PostMapping("/getverifyOpt")
//        public ResponseEntity<?>getverifyOptuser(@RequestParam String verifyOtp,@RequestParam Long userId){
//        try {
//            return new ResponseEntity(new EntityResponse(iUserService.getverifyOtpUser(verifyOtp,userId),0),HttpStatus.OK);
//        }catch (Exception e ){
//            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
//        }
//
//        }
        @PostMapping("/changePasswordEncoder")
           public ResponseEntity<?>changePassword(@RequestParam Long userId,
                                                  @RequestParam String oldPasswordUser,
                                                  @RequestParam String newPasswordUser){
        try{
            return new ResponseEntity(new EntityResponse(iUserService.changePassword(userId, oldPasswordUser, newPasswordUser),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
        }

        @GetMapping("/sendOTP")
         public ResponseEntity<?>sendOTP(@RequestParam String userName){
        try{
            return new ResponseEntity(new EntityResponse(iUserService.sendOTP(userName),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
        }
        @GetMapping("/verifyOTP")
         public ResponseEntity<?>verifyOTP(@RequestParam String useName,Long OTP) {
            try {
                return new ResponseEntity(new EntityResponse(iUserService.verifyOTP(useName, OTP), 0), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
            }
        }


}
