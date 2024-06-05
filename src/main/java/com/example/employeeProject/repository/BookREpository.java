package com.example.employeeProject.repository;

import com.example.employeeProject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookREpository extends JpaRepository<Book,Long> {
}
