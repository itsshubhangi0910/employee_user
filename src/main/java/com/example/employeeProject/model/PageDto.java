package com.example.employeeProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageDto {
    private Object Data;
    private Long totalPages;
    private Integer totalElements;
    private Integer pageSize;
}
