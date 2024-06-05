package com.example.employeeProject.repository;

import com.example.employeeProject.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;



public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //@Query(value = "SELECT  * FROM `employee`",nativeQuery = true)
    // Page<Pageable> getAllPagination(Pageable pageable);

    //@Query(value = "SELECT  * FROM `employee`",nativeQuery = true)
    // List<Pageable> getAll(Pageable pageable);

    @Query(value = "SELECT  * FROM `employee`", nativeQuery = true)
    Page<Employee> getAllPagination(Pageable pageable);

    @Query(value = "SELECT * FROM `employee` WHERE created_at BETWEEN :startDateTime and :endDateTime", nativeQuery = true)
    Page<Employee> getAllStartDateAndEndDate(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    @Query(value = "SELECT first_name AS firstName, last_name AS lastName, employee_city AS city FROM employee", nativeQuery = true)
    Page<EmployeeProjection> getAllProjection(Pageable pageable);

    @Query(value = "SELECT * FROM `employee` WHERE employee_mobole_no LIKE %:mobileNo%", nativeQuery = true)
    Page<Employee> getMobileNo(String mobileNo, Pageable pageable);

    @Query(value = "SELECT * FROM `employee` WHERE employee_city LIKE %:city%", nativeQuery = true)
    Page<Employee> getCity(String city, Pageable pageable);

    @Query(value = "SELECT * FROM `employee` WHERE employee_email LIKE %:eMail%", nativeQuery = true)
    Page<Employee> getEMail(String eMail, Pageable pageable);

    @Query(value = "SELECT employee_mobole_no AS monbileNo,employee_city AS city,employee_email as eMail", nativeQuery = true)
    Page<Employee> getAllMobileNoAndCityAndEMail(String mobileNo, String city, String eMail, Pageable pageable);

    boolean existsByMobileNo(String mobileNo);

}



