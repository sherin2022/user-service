package com.example.demo.dto;

import com.example.demo.enums.BloodGroup;
import com.example.demo.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String id;
    @NotBlank(message = "First name must not be blank")
    @NotNull(message = "First name should not be null")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    @NotNull(message = "Last name should not be null")
    private String lastName;
    @NotBlank(message = "Middle name must not be blank")
    @NotNull(message = "Middle name should not be null")
    private String middleName;
    @NotBlank(message = "Phone number must not be blank")
    @NotNull(message = "Phone number should not be null")
    @Size(min = 10, max = 10, message = "Phone number should be a 10 digit number")
    private String phoneNumber;
    @NotBlank(message = "Email should not be blank")
    @NotNull(message = "Email should not be null")
    @Email(message = "Please enter a valid email address")
    private String email;
    @NotBlank(message = "Address should not be blank")
    @NotNull(message = "Address should not be null")
    private String address;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;
    private Gender gender;
    @NotBlank(message = "Employee number should not be blank")
    @NotNull(message = "Employee number should not be null")
    private String employeeId;
    private BloodGroup bloodGroup;
   // @NotBlank(message = "password must not be blank")
   // @NotNull(message = "password should not be null")
    @Size(min = 4, message = "Password should have at least 8 characters")
    private String password;


}
