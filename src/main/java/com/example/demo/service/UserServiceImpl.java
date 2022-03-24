package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDto createUser(UserRequest userRequest) {
        User newUser = new User();

        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setMiddleName(userRequest.getMiddleName());
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setEmail(userRequest.getEmail());
        newUser.setDateOfBirth(userRequest.getDateOfBirth());
        newUser.setGender(userRequest.getGender());
        newUser.setEmployeeId(userRequest.getEmployeeId());
        newUser.setBloodGroup(userRequest.getBloodGroup());
        newUser.setPassword(userRequest.getPassword());
        newUser = userRepo.save(newUser);
        return new UserDto(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getMiddleName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getAddress(), newUser.getDateOfBirth(), newUser.getEmployeeId(), newUser.getBloodGroup(), newUser.getGender());
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> allUsers = userRepo.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : allUsers) {
            userDtoList.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getDateOfBirth(), user.getEmployeeId(), user.getBloodGroup(), user.getGender()));
        }
        return userDtoList;
    }

    @Override
    public User getUserDetails(String id) {
        User userInfo = userRepo.findById(id).get();
        return userInfo;
    }

    @Override
    public User updateUser(UserRequest userRequest, String userId) {
        Optional<User> userToBeUpdated = userRepo.findById(userId);
        userToBeUpdated.get().setFirstName(userRequest.getFirstName());
        userToBeUpdated.get().setMiddleName(userRequest.getMiddleName());
        userToBeUpdated.get().setLastName(userRequest.getLastName());
        userToBeUpdated.get().setGender(userRequest.getGender());
        userToBeUpdated.get().setBloodGroup(userRequest.getBloodGroup());
        userToBeUpdated.get().setDateOfBirth(userRequest.getDateOfBirth());
        userToBeUpdated.get().setEmail(userRequest.getEmail());
        userToBeUpdated.get().setPassword(userRequest.getPassword());
        userToBeUpdated.get().setPhoneNumber(userRequest.getPhoneNumber());
        return userRepo.save(userToBeUpdated.get());

    }

    @Override
    public String deleteUser(String id) {
        Optional<User> userToBeDeleted = userRepo.findById(id);
        userRepo.deleteById(userToBeDeleted.get().getId());
        return userToBeDeleted.get().getId();
    }

    @Override
    public UserDto getUserById(String userId) {
        Optional<User> selectedUser = userRepo.findById(userId);
        if (selectedUser.isPresent()) {
            User identifiedUser = selectedUser.get();

            return new UserDto(identifiedUser.getId(), identifiedUser.getFirstName(), identifiedUser.getLastName(), identifiedUser.getMiddleName(), identifiedUser.getPhoneNumber(), identifiedUser.getEmail(), identifiedUser.getAddress(), identifiedUser.getDateOfBirth(), identifiedUser.getEmployeeId(), identifiedUser.getBloodGroup(), identifiedUser.getGender());
        }
        else return null;

    }


}