package com.example.demo.controller;

import com.example.demo.dto.UserWithoutPassword;
import com.example.demo.dto.UserRequest;
import com.example.demo.enums.BloodGroup;
import com.example.demo.enums.Gender;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.example.demo.enums.BloodGroup.A_NEG;
import static com.example.demo.enums.Gender.MALE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    void testCreateUser() throws Exception {
        UserRequest userRequest = createTestUserRequest();
        UserWithoutPassword userDto = createTestUserDto();
        Mockito.when(userService.createUser(Mockito.any(UserRequest.class))).thenReturn(userDto);
        MvcResult requestResult = mockMvc.perform(post("/users")
                        .content(asJsonString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<UserWithoutPassword> usersDto = createUserDtoList();
        Mockito.when(userService.getUsers(1,2)).thenReturn(usersDto);
        mockMvc.perform(get("/users?page=1&pageSize=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("firstTest")));
    }

    private UserWithoutPassword createTestUserDto() {
        Date dob = new Date();
        UserWithoutPassword userDto = new UserWithoutPassword("1","Sagar","Jackey","Alias","3764374364","rajaudi1234@gmail.com","Bangalore",dob,"6965",A_NEG,MALE);
        return userDto;

    }

    private UserRequest createTestUserRequest() {
        UserRequest userRequest=new UserRequest();
        userRequest.setId("1");
        userRequest.setFirstName("Sagar");
        userRequest.setLastName("Jackey");
        userRequest.setMiddleName("Alias");
        userRequest.setPhoneNumber("3764374364");
        userRequest.setEmail("rajaudi1234@gmail.com");
        userRequest.setAddress("Bangalore");
        Date dob = new Date();
        userRequest.setDateOfBirth(dob);
        userRequest.setEmployeeId("6965");
        userRequest.setBloodGroup(A_NEG);
        userRequest.setGender(MALE);
        userRequest.setPassword("******6568");
        return userRequest;
    }

    private List<UserWithoutPassword> createUserDtoList() {
        List<UserWithoutPassword> usersDto = new ArrayList<>();
        UserWithoutPassword userDto1 = new UserWithoutPassword();
        userDto1.setId("1");
        userDto1.setFirstName("firstTest");
        userDto1.setMiddleName("J");
        userDto1.setLastName("S");
        userDto1.setPhoneNumber("9090909090");
        userDto1.setEmail("firsttest@mail.com");
        userDto1.setDateOfBirth(new Date());
        userDto1.setEmployeeId("12345");
        userDto1.setBloodGroup(BloodGroup.O_POS);
        userDto1.setGender(Gender.MALE);

        UserWithoutPassword userDto2 = new UserWithoutPassword();
        userDto2.setId("2");
        userDto2.setFirstName("secondTest");
        userDto2.setMiddleName("J");
        userDto2.setLastName("S");
        userDto2.setPhoneNumber("9090909090");
        userDto2.setEmail("secondtest@mail.com");
        userDto2.setDateOfBirth(new Date());
        userDto2.setEmployeeId("12346");
        userDto2.setBloodGroup(BloodGroup.O_POS);
        userDto2.setGender(Gender.MALE);

        usersDto.add(userDto1);
        usersDto.add(userDto2);
        return usersDto;
    }
}