package com.example.employeeProject.controller;

import com.example.employeeProject.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiAddress")
public class AddressController {

    @Autowired
    private IAddressService iAddressService;
}
