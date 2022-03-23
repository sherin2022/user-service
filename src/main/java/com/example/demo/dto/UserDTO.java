package com.example.demo.dto;
import com.example.demo.model.BloodGroup;
import com.example.demo.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;
    private String employeeNumber;
    private BloodGroup bloodGroup;
    private Gender gender;
}