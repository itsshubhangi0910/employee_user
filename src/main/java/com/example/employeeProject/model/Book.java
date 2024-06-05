package com.example.employeeProject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @Column(name = "book_title")
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_author_id")
    private Author author;


}
