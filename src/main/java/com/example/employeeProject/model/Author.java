package com.example.employeeProject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long authorId;

    @Column(name = "first_name")
    private String  firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "language")
    private String language;

    @OneToOne(mappedBy = "author")
    private Book book;

}
