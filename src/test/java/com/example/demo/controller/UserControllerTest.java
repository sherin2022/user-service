

package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.enums.BloodGroup;
import com.example.demo.enums.Gender;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.demo.enums.BloodGroup.A_NEG;
import static com.example.demo.enums.Gender.FEMALE;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void createUserTest() throws Exception {
        UserRequest userRequest = createNewUser();
        UserDto userDto = createUserDto();
        Mockito.when(userService.createUser(userRequest)).thenReturn(userDto);
        mockMvc.perform(post("/users")
                        .content(asJsonString(userDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
    private UserDto createUserDto() {
        Date dob = new Date();
        UserDto userDto = new UserDto("1", "savita", "Go", "Bhi", "8765432567", "savita@gmail.com", "Bangalore", dob, "0001", A_NEG, FEMALE);
        return userDto;

    }

    private UserRequest createNewUser() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId("1");
        userRequest.setFirstName("savita");
        userRequest.setMiddleName("bhi");
        userRequest.setLastName("Go");
        userRequest.setPhoneNumber("8765432567");
        userRequest.setEmail("ra1@gmail.com");
        userRequest.setAddress("Bangalore");
        Date dob = new Date();
        userRequest.setDateOfBirth(dob);
        userRequest.setGender(FEMALE);
        userRequest.setEmployeeId("0001");
        userRequest.setBloodGroup(A_NEG);
        userRequest.setPassword("raj123");
        return userRequest;
    }


}

