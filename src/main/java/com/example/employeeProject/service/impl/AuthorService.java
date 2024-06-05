package com.example.employeeProject.service.impl;

import com.example.employeeProject.model.Author;
import com.example.employeeProject.model.request.AuthorRequest;
import com.example.employeeProject.repository.AuthorRepository;
import com.example.employeeProject.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Object saveAuthor(AuthorRequest authorRequest) {
       if (authorRepository.existsById(authorRequest.getAuthorId())){
           Author author=authorRepository.findById(authorRequest.getAuthorId()).get();
           author.setFirstName(authorRequest.getFirstName());
           author.setLastName(authorRequest.getLastName());
           author.setLanguage(authorRequest.getLanguage());
           authorRepository.save(author);
           return "update";

       }else {
           Author author=new Author();
           author.setFirstName(authorRequest.getFirstName());
           author.setLastName(authorRequest.getLastName());
           author.setLanguage(authorRequest.getLanguage());
           authorRepository.save(author);
           return "save data";
       }
    }
}
