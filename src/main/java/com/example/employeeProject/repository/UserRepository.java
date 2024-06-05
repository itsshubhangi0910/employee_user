package com.example.employeeProject.repository;

import com.example.employeeProject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUserName(Long userName);


    @Query(value = "SELECT c.password FROM User as c WHERE c.userId = :userId", nativeQuery = false)
    String getPasswordByUserId(Long userId);

   // Page<User> getAllStartDateAndEndDate(String startDate, String endDate, Pageable pageable);


@Query(value = "SELECT * FROM `user`",nativeQuery = true)
    Page<User> getAll(Pageable pageable);

@Query(value = "SELECT * FROM `user` WHERE created_at BETWEEN :startDateTime and :endDateTime",nativeQuery = true)
    Page<User> getStartDateAndEndDate(LocalDateTime startDateTime,
                                      LocalDateTime endDateTime, Pageable pageable);
@Query(value = "SELECT  * FROM `user` WHERE CONCAT(user_name,' ',user_password) LIKE %:search%",nativeQuery = true)
    Page<User> getAllSearch(String search, Pageable pageable);

@Query(value = "SELECT * FROM `user` WHERE user_name LIKE %:userName%",nativeQuery = true)
    Page<User> getAllUserName(String userName, Pageable pageable);

@Query(value = "SELECT * FROM `user` WHERE user_password LIKE %:password%",nativeQuery = true)
    Page<User> getAllPassword(String password, Pageable pageable);


    boolean existsByUserName(String userName);


    // @Query(value = "SELECT *  FROM `user` WHERE CONCAT(user_name,' ',user_password) LIKE %:search%",nativeQuery = true)
    //Page<User> getAllSearchAndUsernameAndPassword(String search, String userName, String password, Pageable pageable);

    //Page<User> findBySearchAndUsernameAndPassword(String search, String userName, String password, Pageable pageable);
}
