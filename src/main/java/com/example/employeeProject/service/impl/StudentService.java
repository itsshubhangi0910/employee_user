package com.example.employeeProject.service.impl;

import com.example.employeeProject.model.Student;
import com.example.employeeProject.repository.StudentRepository;
import com.example.employeeProject.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressService addressService;

    @Override
    public Object saveOrUpdateStudent(Student student) {
        return null;
    }
}
