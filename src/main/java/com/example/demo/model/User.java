package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")

public class User {

 @Id
 String userId;

 @NotBlank(message = "firstName cannot be empty")
 String firstName;

 String middleName;
 String lastName;

 @Size(min=10,max=10)
 String phoneNumber;
 String dateOfBirth;
 String gender;
 String martialStatus;
 String employeeNumber;
 String bloodGroup;

 @NotBlank(message = "email cannot be empty")
 String email;

 @NotBlank(message = "password cannot be empty")
 String password;



}
