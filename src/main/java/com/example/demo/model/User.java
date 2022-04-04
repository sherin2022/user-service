package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
 @Id
 private String userId;
 private String firstName;
 private String lastName;
 private String middleName;
 private String phoneNumber;
 private String email;
 private Date dateOfBirth;
 private Gender gender;
 private String employeeId;
 private BloodGroup bloodGroup;
 private String password;
}