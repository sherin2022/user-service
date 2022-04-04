package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;


import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Date;

import static com.example.demo.enums.BloodGroup.A_NEG;
import static com.example.demo.enums.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {

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
                        .content(asJsonString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    private UserDto createUserDto() {
        Date dob = new Date();
        UserDto userDto = new UserDto("1","Sagar","Alias","Jackey","376437436","rajaudi1234@gmail.com","Bangalore",dob,"6965",A_NEG,MALE);
        return userDto;

    }

    private UserRequest createNewUser() {
        UserRequest userRequest=new UserRequest();
        userRequest.setId("1");
        userRequest.setFirstName("Sagar");
        userRequest.setMiddleName("Alias");
        userRequest.setLastName("Jackey");
        userRequest.setAddress("Bangalore");
        userRequest.setPhoneNumber("376437436");
        Date dob = new Date();
        userRequest.setDateOfBirth(dob);
        userRequest.setGender(MALE);
        userRequest.setBloodGroup(A_NEG);
        userRequest.setEmployeeId("6965");
        userRequest.setEmail("rajaudi1234@gmail.com");
        userRequest.setPassword("******656");
        return userRequest;
    }
}