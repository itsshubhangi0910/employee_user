package com.example.employeeProject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    private  Long userId;
    private String userName;
    private String password;

}
