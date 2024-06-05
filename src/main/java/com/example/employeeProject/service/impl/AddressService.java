package com.example.employeeProject.service.impl;

import com.example.employeeProject.repository.AddressRepository;
import com.example.employeeProject.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

}
