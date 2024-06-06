package com.example.employeeProject.service.impl;
import com.example.employeeProject.Utils.OTPService;
import com.example.employeeProject.model.User;
import com.example.employeeProject.model.request.UserRequest;
import com.example.employeeProject.model.response.UserResponse;
import com.example.employeeProject.repository.UserRepository;
import com.example.employeeProject.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OTPService otpService;

    @Override
    public Object userLoginUser(UserRequest userRequest) throws Exception {
        if (userRepository.existsById(userRequest.getUserId())) {
            User user = userRepository.findById(userRequest.getUserId()).get();
            user.setUserName(userRequest.getUserName());
            user.setPassword(userRequest.getPassword());
            userRepository.save(user);
            return "updated";
        } else {
            User user = new User();
            if (userRepository.existsByUserName(userRequest.getUserName())){
                throw new Exception("userName already exists");
            }else {
                user.setUserName(userRequest.getUserName());
            }
            user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
            int otp=otpService.generateOTP(userRequest.getUserName());
            System.out.println("OTP: "+otp);
            user.setOTP(otp);
            user.setActive(true);
            user.setDeleted(false);
            userRepository.save(user);
            this.sendMail("Application",otp+" " + "is your gmail verification code.",userRequest.getUserName());
            return "save data";
        }
    }

    @Override
    public Object changePasswordUser(Long userId, String newPassword, String oldPassword) throws Exception {
        User user = userRepository.findById(userId).get();
        //  String passwordByUserId = userRepository.getPasswordByUserId(userId);
        String oldPasswordDatabse = user.getPassword();
        if (oldPassword.matches(oldPasswordDatabse)) {
            if (newPassword.matches(oldPasswordDatabse)) {
                throw new Exception("New Password not same as old password please use another");
            } else {
                user.setPassword(newPassword);
                userRepository.save(user);
            }
        } else {
            throw new Exception("old Password is incorrect");
        }
        return user;
    }
    @Override
    public Object startDateEndDateUser(String startDate, String endDate, Pageable pageable) {
        Page<User> users;
        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
            LocalDate startDate1 = LocalDate.parse(startDate);
            LocalDate endDate1 = LocalDate.parse(endDate);

            LocalDateTime startDateTime = LocalDateTime.of(startDate1, LocalTime.of(0, 0));
            LocalDateTime endDateTime = LocalDateTime.of(endDate1, LocalTime.of(23, 59));
            users = userRepository.getStartDateAndEndDate(startDateTime, endDateTime, pageable);
        } else {
            users = userRepository.getAll(pageable);
        }
        return users;
    }

    @Override
    public Object getAllPaginationUser(Pageable pageable) {
        Page<User> all = userRepository.getAll(pageable);
        return all;
    }

    @Override
    public Object getByIdUser(Long userId) throws Exception {
        if (userRepository.existsById(userId)) {
            User user = userRepository.findById(userId).get();
            return user;
        } else {
            throw new Exception("id not found");
        }
    }

    @Override
    public Object getSearchingUser(String search, Pageable pageable) {
        Page<User> users;
        if (search != null) {
            users = userRepository.getAllSearch(search, pageable);
        } else {
            users = userRepository.getAll(pageable);
        }
        return users;
    }

//    @Override
//    public Object getverifyOtpUser(String verifyOtp, Long userId) throws Exception {
//
//            User user = userRepository.findById(userId).get();
//            int otpDatabase = user.getOTP();
//            System.out.println(user);
//            UserResponse userResponse= new UserResponse();
//            if (verifyOtp.matches(String.valueOf(otpDatabase))) {
//              userResponse.setResponseData(user);
//              userResponse.setStatus("verified");
//              userResponse.setName(user.getUserName());
//               return userResponse;
//            } else {
//                throw new Exception(" not verify");
//            }
//    }

    @Override
    public Object changePassword(Long userId, String oldPasswordUser, String newPasswordUser) throws Exception {
        User user=userRepository.findById(userId).orElseThrow(()->new Exception("userId not found"));
        if (bCryptPasswordEncoder.matches(oldPasswordUser,user.getPassword())){
            if (bCryptPasswordEncoder.matches(newPasswordUser,user.getPassword())){
                user.setPassword(bCryptPasswordEncoder.encode(newPasswordUser));
                userRepository.save(user);
                return "password change successfully";
            }else {
                throw new Exception("new password cannot same as the old password");
            }
        }else {
            throw new Exception("old password is incorrect");


        }
    }

    @Override
    public Object sendOTP(String userName) throws Exception {
        if (userRepository.existsByUserName(userName)){

            otpService.clearOTP(userName);
            int otp=otpService.generateOTP(userName);
            return "Otp send successfully on this Email";

        }else{
            throw new Exception("This user is not valid");
        }
    }

    @Override
    public Object verifyOTP(String useName, Long otp) throws Exception {
        if (userRepository.existsByUserName(useName)){
            int generateOTP = otpService.getOTP(useName);
            if (otp==generateOTP){
                otpService.clearOTP(useName);

                return "verify otp successfully";
            }else {
                throw new Exception("otp is incorrect");
            }
        }else {
            throw new Exception("This user is not valid");
        }
    }

    public  void sendMail(String subject,String msg,String to){
        //JavaMailSender javaMailSender = null;
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject(subject);
    message.setTo(to);
    message.setText(msg);
    javaMailSender.send(message);
}

//public String generateOTP()
//{
//    int randomNo=(int) (Math.random()*900000+100000);
//
//    String otp=String.valueOf(randomNo);
//    return otp;
//}

}


