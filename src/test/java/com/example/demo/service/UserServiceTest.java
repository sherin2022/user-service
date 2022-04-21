package com.example.demo.service;

import com.example.demo.constantException.UserConstantException;
import com.example.demo.dto.UserWithoutPassword;
import com.example.demo.dto.UserRequest;
import com.example.demo.enums.BloodGroup;
import com.example.demo.enums.Gender;
import com.example.demo.exception.EmailAlreadyExistException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.demo.enums.BloodGroup.A_NEG;
import static com.example.demo.enums.Gender.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void testCreateUser() {
        UserWithoutPassword userDto = createTestUserDto();
        UserRequest userRequest = createTestUserRequest();
        User user = new User();
        user.setFirstName("sherin");
        user.setMiddleName("J");
        user.setLastName("J");
        user.setPhoneNumber("9090909090");
        user.setEmail("sherin@mail.com");
        user.setDateOfBirth(new Date());
        user.setEmployeeId("1234");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("1234**");

        when(userRepo.save(Mockito.any(User.class))).thenReturn(user);
        User newUserRecord = userRepo.save(user);
        when(userService.createUser(userRequest)).thenReturn(userDto);
        assertThat(newUserRecord.getFirstName()).isNotNull();
        assertThat(newUserRecord.getEmail()).isEqualTo("sherin@mail.com");
    }
    @Test
    void testExceptionThrownWhenEmailAlreadyRegistered() {
        User existingUserRecord = createOneUserToCheck();
        User duplicateUserEntry = createOneUserToUpdate();
        UserRequest userRequest = new UserRequest();
        Optional<User> ofResult = Optional.of(duplicateUserEntry);
        when(this.userRepo.save((User) any())).thenReturn(existingUserRecord);
        when(this.userRepo.findByEmail((String) any())).thenReturn(ofResult);
        assertThrows(EmailAlreadyExistException.class, () -> this.userService.createUser(userRequest));
        verify(this.userRepo).findByEmail((String) any());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser("1");
        verify(userRepo, times(1)).deleteById("1");
    }

    @Test
    void testExceptionThrownWhenUserNotFound() {
        Mockito.doThrow(UserNotFoundException.class).when(userRepo).deleteById("5");
        Exception userNotFoundException = assertThrows(UserNotFoundException.class, () -> userService.deleteUser("5"));
        assertTrue(userNotFoundException.getMessage().contains(UserConstantException.USERNOTFOUND));
    }

    @Test
    void testExceptionThrownWhenUserIdNotFound() {
        User user = createOneUserToUpdate();
        UserWithoutPassword userDto = createTestUserDto();
        UserRequest userRequest = createTestUserRequest();
        Mockito.when(userRepo.findById("1")).thenReturn(Optional.ofNullable(user));
        assertThat(userService.updateUser(userRequest, "1")).isEqualTo(userDto);
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userRequest, null));
    }

    @Test
    void testGetAllUsers() {
        User user = createOneUserToCheck();
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        PageImpl<User> pageImpl = new PageImpl<>(users);

        when(this.userRepo.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, this.userService.getUsers(1, 1).size());
        verify(this.userRepo).findAll((org.springframework.data.domain.Pageable) any());
    }

    private List<User> createUsersList1() {
        List<User> users = new ArrayList<>();
        User UserWithOutPassword1 = new User();
        UserWithOutPassword1.setId("1");
        UserWithOutPassword1.setFirstName("firstTest");
        UserWithOutPassword1.setMiddleName("J");
        UserWithOutPassword1.setLastName("S");
        UserWithOutPassword1.setPhoneNumber("9090909090");
        UserWithOutPassword1.setEmail("prabhu@mail.com");
        UserWithOutPassword1.setDateOfBirth(new Date());
        UserWithOutPassword1.setEmployeeId("12345");
        UserWithOutPassword1.setBloodGroup(BloodGroup.O_POS);
        UserWithOutPassword1.setGender(Gender.MALE);

        User UserWithOutPassword2 = new User();
        UserWithOutPassword2.setId("2");
        UserWithOutPassword2.setFirstName("secondTest");
        UserWithOutPassword2.setMiddleName("J");
        UserWithOutPassword2.setLastName("S");
        UserWithOutPassword2.setPhoneNumber("9090909090");
        UserWithOutPassword2.setEmail("prabhu@mail.com");
        UserWithOutPassword2.setDateOfBirth(new Date());
        UserWithOutPassword2.setEmployeeId("12345");
        UserWithOutPassword2.setBloodGroup(BloodGroup.O_POS);
        UserWithOutPassword2.setGender(Gender.MALE);

        users.add(UserWithOutPassword1);
        users.add(UserWithOutPassword2);
        return users;
    }

    private List<UserWithoutPassword> createUsersList() {
        List<UserWithoutPassword> userDtoList = new ArrayList<>();
        UserWithoutPassword userDto = new UserWithoutPassword();
        userDto.setId("2");
        userDto.setFirstName("Prithvi");
        userDto.setMiddleName("Raj");
        userDto.setLastName("Chauhan");
        userDto.setPhoneNumber("9846160222");
        userDto.setEmail("prithviraj@mail.com");
        userDto.setDateOfBirth(new Date());
        userDto.setEmployeeId("7048");
        userDto.setBloodGroup(BloodGroup.O_POS);
        userDto.setGender(Gender.MALE);
        userDtoList.add(userDto);
        return userDtoList;
    }

    private UserWithoutPassword createTestUserDto() {
        Date dob = new Date();
        UserWithoutPassword userDto = new UserWithoutPassword("1","Sagar","Jackey","Alias","3764374364","rajaudi1234@gmail.com","Bangalore",dob,"1234",A_NEG,MALE);
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
        userRequest.setEmployeeId("1234");
        userRequest.setBloodGroup(A_NEG);
        userRequest.setGender(MALE);
        userRequest.setPassword("******6568");
        return userRequest;
    }

    private User createOneUserToUpdate() {
        User user = new User();
        user.setId("1");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("sherin@mail.com");
        user.setAddress("Bangalore");
        user.setDateOfBirth(new Date());
        user.setEmployeeId("1234");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("**trr44");

        return user;
    }

    private User createOneUserToCheck() {
        User user = new User();
        user.setId("1");
        user.setFirstName("firstTest");
        user.setMiddleName("J");
        user.setLastName("S");
        user.setPhoneNumber("9090909090");
        user.setEmail("sherin@mail.com");
        user.setAddress("Bangalore");
        user.setDateOfBirth(new Date());
        user.setEmployeeId("1234");
        user.setBloodGroup(BloodGroup.O_POS);
        user.setGender(Gender.MALE);
        user.setPassword("**trr44");

        return user;
    }
}



