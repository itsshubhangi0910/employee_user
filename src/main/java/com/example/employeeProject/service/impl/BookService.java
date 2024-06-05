package com.example.employeeProject.service.impl;

import com.example.employeeProject.model.Book;
import com.example.employeeProject.model.request.BookRequest;
import com.example.employeeProject.repository.BookREpository;
import com.example.employeeProject.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookREpository bookREpository;


    @Override
    public Object saveBook(BookRequest bookRequest) {
        if (bookREpository.existsById(bookRequest.getBookId())){
            Book book=bookREpository.findById(bookRequest.getBookId()).get();
            book.setTitle(bookRequest.getTitle());
            book.setAuthor(bookRequest.getAuthorId());
            bookREpository.save(book);
            return "update";
        }else {
            Book book=new Book();
            book.setTitle(bookRequest.getTitle());
            book.setAuthor(bookRequest.getAuthorId());
            bookREpository.save(book);
            return "save data";
        }
    }
}
