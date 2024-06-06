package com.example.employeeProject.service.impl;

import com.example.employeeProject.model.Employee;
import com.example.employeeProject.model.PageDto;
import com.example.employeeProject.model.request.EmployeeRequest;
import com.example.employeeProject.repository.EmployeeProjection;
import com.example.employeeProject.repository.EmployeeRepository;
import com.example.employeeProject.service.IEmployeeService;
import org.apache.commons.lang3.StringUtils;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Object saveOrUpdateEmployee(EmployeeRequest employeeRequest) throws Exception {
        if (employeeRepository.existsById(employeeRequest.getEmployeeId())) {
            Employee employee = employeeRepository.findById(employeeRequest.getEmployeeId()).get();
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setCity(employeeRequest.getCity());
            employee.setEMail(employeeRequest.getEMail());
            employee.setMobileNo(employeeRequest.getMobileNo());
            employee.setGender(employeeRequest.getGender());
            //employee.setEmployeeCode(this.employeeCodeGenerate(employeeRequest.getFirstName(),));
            employeeRepository.save(employee);
            return "updated";

        } else {
            Employee employee = new Employee();
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setMobileNo(employeeRequest.getMobileNo());
            employee.setGender(employeeRequest.getGender());
            if (employeeRepository.existsByMobileNo(employeeRequest.getMobileNo())){
                throw new Exception("Mobile no already exists");
            }else {
                employee.setMobileNo(employeeRequest.getMobileNo());
            }
            employee.setCity(employeeRequest.getCity());
            employee.setEMail(employeeRequest.getEMail());
            employee.setFile(this.fileUpload(employeeRequest.getFile()));
            employee.setActive(true);
            employee.setDeleted(false);
            employeeRepository.save(employee);
            Long employeeId = employee.getEmployeeId();
            employee.setEmployeeCode(this.employeeCodeGenerate(employeeRequest.getFirstName(), employeeId));
            employeeRepository.save(employee);

        }
        return "save data";
    }

    @Override
    public Object getById(Long employeeId) throws Exception {
        return null;
    }

    @Override
    public Object getByIdEmployee(Long employeeId) throws Exception {
        if (employeeRepository.existsById(employeeId)) {
            Employee employee = employeeRepository.findById(employeeId).get();
            return employee;
        } else {
            throw new Exception("id not found");
        }
    }

    @Override
    public Object getAllEmployee() {
        List<Employee> all = employeeRepository.findAll();
        return all;

    }

    @Override
    public Object softDeleteEmployee(Long employeeId) throws Exception {
        if (employeeRepository.existsById(employeeId)) {
            Employee employee = employeeRepository.findById(employeeId).get();
            employee.setDeleted(true);
            employeeRepository.save(employee);
            return "deleted";

        } else {
            throw new Exception("id not deleted");
        }
    }

    @Override
    public Object getAllPaginationEmployee(Pageable pageable) {
        Page<Employee> allPagination = employeeRepository.getAllPagination(pageable);
        return new PageDto(allPagination.getContent(), allPagination.getTotalElements(), allPagination.getTotalPages(), allPagination.getNumberOfElements());

    }

    @Override
    public Object dateTimeEmployee(String startDate, String endDate, Pageable pageable) {
        Page<Employee> employees;
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            LocalDate startDate1 = LocalDate.parse(startDate);
            LocalDate endDate1 = LocalDate.parse(endDate);

            LocalDateTime startDateTime = LocalDateTime.of(startDate1, LocalTime.of(0, 0));
            LocalDateTime endDateTime = LocalDateTime.of(endDate1, LocalTime.of(23, 59));
            employees = employeeRepository.getAllStartDateAndEndDate(startDateTime, endDateTime, pageable);
        } else {
            employees = employeeRepository.getAllPagination(pageable);
        }
        return new PageDto(employees.getContent(), employees.getTotalElements(), employees.getTotalPages(), employees.getNumberOfElements());
    }

    @Override
    public String fileUpload(MultipartFile userFile) throws Exception {
        if (!userFile.isEmpty() && userFile != null) {
            String storagePath = "C:\\Users\\oms-desktop\\Downloads\\picture";
            String originalFilename = userFile.getOriginalFilename();
            Path path = Paths.get(storagePath, originalFilename);
            Files.write(path, userFile.getBytes());
            return originalFilename;
        }else {
            throw new Exception("file uploaded");
        }
    }

    @Override
    public Object getProjectionEmployee(Pageable pageable) {
        Page<EmployeeProjection> employeeProjections;
        employeeProjections = employeeRepository.getAllProjection(pageable);
        return employeeProjections;
    }

    @Override
    public Object getSearchingEmployee(String mobileNo, String city, String eMail, Pageable pageable) {
        Page<Employee> employees;
        if (mobileNo != null) {
            employees = employeeRepository.getMobileNo(mobileNo, pageable);
        } else if (city != null) {
            employees = employeeRepository.getCity(city, pageable);
        } else if (eMail != null) {
            employees = employeeRepository.getEMail(eMail, pageable);
        } else if (mobileNo != null && city != null && eMail != null) {
            employees = employeeRepository.getAllMobileNoAndCityAndEMail(mobileNo,city,eMail,pageable);
        } else {
            employees = employeeRepository.getAllPagination(pageable);
        }
        return employees;

    }








    /*@Override
    public Object getProjectionEmployee() {
       List<EmployeeProjection> employeeProjections = null;
       employeeProjections=employeeRepository.getFirstNameAndLastNameAndCity();
        return employeeProjections;
    }*/

   /* @Override
    public Object getProjectionEmployee(String firstName, String lastName, String city) {
        List<Employee>employees;
        employeeRepository.getAllFirstNameAndLastNameAndCity(firstName,lastName,city);
        return employees;

    }*/

    //codeGenerate
    static int a = 1000;
    public String employeeCodeGenerate(String firstName, Long employeeId) {

    String str=firstName.toUpperCase();
        String employeechar=str.substring(0,3);
        String str1=employeechar+a+employeeId;
        System.out.println("employee code "+str1);
        return str1;


    }







}    //fos.write(userFile.getBytes());
            //fos.close();
            //return new ResponseEntity(" The file uploaded successfully", HttpStatus.OK);
       /* boolean f= false;
        try{
            InputStream is=userFile.getInputStream();
            byte data[]=new byte[is.available()];
            is.read(data);
            FileOutputStream fos=new FileOutputStream((File.separator+userFile.getOriginalFilename()));
            fos.write(data);
            fos.close();
            f=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }*/


