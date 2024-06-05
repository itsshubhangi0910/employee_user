package com.example.employeeProject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Add-Info")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long addressId;

    @Column(name = "address")
    private String address;

    @OneToOne
    private Student student;



}

