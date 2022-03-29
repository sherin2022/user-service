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
        newUser.setAddress(userRequest.getAddress());
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
    public UserDto getUserDetails(String id) {
        User userInfo = userRepo.findById(id).get();
        UserDto userDtoInfo = new UserDto(userInfo.getId(),userInfo.getFirstName(),userInfo.getLastName(),userInfo.getMiddleName(),userInfo.getPhoneNumber(),userInfo.getEmail(),userInfo.getAddress(),userInfo.getDateOfBirth(),userInfo.getEmployeeId(),userInfo.getBloodGroup(),userInfo.getGender());
        return userDtoInfo;
    }

    @Override
    public User updateUser(UserRequest userRequest, String userId) {
        Optional<User> userToBeUpdated = userRepo.findById(userId);
        if(userRequest.getFirstName()!=null)
            userToBeUpdated.get().setFirstName(userRequest.getFirstName());
        if(userRequest.getMiddleName()!=null)
            userToBeUpdated.get().setMiddleName(userRequest.getMiddleName());
        if(userRequest.getLastName()!=null)
            userToBeUpdated.get().setLastName(userRequest.getLastName());
        if(userRequest.getGender()!=null)
            userToBeUpdated.get().setGender(userRequest.getGender());
        if(userRequest.getBloodGroup()!=null)
         userToBeUpdated.get().setBloodGroup(userRequest.getBloodGroup());
        if(userRequest.getDateOfBirth()!=null)
            userToBeUpdated.get().setDateOfBirth(userRequest.getDateOfBirth());
        if(userRequest.getEmail()!=null)
            userToBeUpdated.get().setEmail(userRequest.getEmail());
        if(userRequest.getPassword()!=null)
            userToBeUpdated.get().setPassword(userRequest.getPassword());
        if(userRequest.getPhoneNumber()!=null)
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