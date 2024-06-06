package com.example.employeeProject.model.request;

import com.example.employeeProject.Utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class
EmployeeRequest {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String eMail;
    private String city;
    private Gender gender;
    private MultipartFile file;


}
