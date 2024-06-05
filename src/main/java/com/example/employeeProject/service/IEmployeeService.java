package com.example.employeeProject.service;

import com.example.employeeProject.model.request.EmployeeRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IEmployeeService {
    Object saveOrUpdateEmployee(EmployeeRequest employeeRequest) throws Exception;

    Object getById(Long employeeId) throws Exception;

    Object getByIdEmployee(Long employeeId) throws Exception;

    Object getAllEmployee();

    Object softDeleteEmployee(Long employeeId) throws Exception;



    Object getAllPaginationEmployee(Pageable pageable);

    Object dateTimeEmployee(String startDate, String endDate, Pageable pageable);

    Object fileUpload(MultipartFile userFile) throws Exception;


    Object getProjectionEmployee(Pageable pageable);

    Object getSearchingEmployee(String mobileNo, String city, String eMail, Pageable pageable);
}
