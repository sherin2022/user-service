package com.example.demo.model;

import com.example.demo.enums.BloodGroup;
import com.example.demo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Document(collection="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    @NotBlank(message = "Please Enter a name") //Need to understand how validation works from a front-end point
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;
    @NotBlank
    @NotNull
    private String middleName;
    @NotBlank
    @NotNull
    private String phoneNumber;
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String Address;
    @NotBlank
    @NotNull
    private LocalDate dateOfBirth;
    @NotBlank
    @NotNull
    private String employeeId;
    @NotBlank
    @NotNull
    private BloodGroup bloodGroup;
    @NotBlank
    @NotNull
    private Gender gender;
    @NotBlank
    @NotNull
    private String Password;




}
