package com.example.employeeProject.model;

import com.example.employeeProject.Utils.Gender;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "employee_mobole_no")
    private String mobileNo;

    @Column(name = "employee_city")
    private String city;

    @Column(name = "employee_email")
    private String eMail;

    @Column(name = "file")
    private String file;

    @Column(name = "employee_code")
    private  String employeeCode;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(name = "is_deleted")
    private boolean isDeleted=false;

    @Column(name = "is_active")
    private  boolean isActive=true;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
