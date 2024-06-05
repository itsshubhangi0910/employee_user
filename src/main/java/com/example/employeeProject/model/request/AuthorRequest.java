package com.example.employeeProject.model.request;

import com.example.employeeProject.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    private Long authorId;
    private String firstName;
    private String lastName;
    private String language;
}
