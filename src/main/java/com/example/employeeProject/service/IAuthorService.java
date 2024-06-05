package com.example.employeeProject.service;

import com.example.employeeProject.model.request.AuthorRequest;

public interface IAuthorService {
    Object saveAuthor(AuthorRequest authorRequest);
}
