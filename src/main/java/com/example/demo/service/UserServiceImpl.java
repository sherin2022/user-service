package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequest;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.constant.UserConstant.USERNOTFOUND;

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
        newUser.setAddress(userRequest.getAddress());
        newUser.setDateOfBirth(userRequest.getDateOfBirth());
        newUser.setGender(userRequest.getGender());
        newUser.setEmployeeId(userRequest.getEmployeeId());
        newUser.setBloodGroup(userRequest.getBloodGroup());
        newUser.setPassword(userRequest.getPassword());
        newUser = userRepo.save(newUser);
        return new UserDto(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getMiddleName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getAddress(), newUser.getDateOfBirth(), newUser.getEmployeeId(), newUser.getBloodGroup(), newUser.getGender());
    }

    @Override
    public List<UserDto> getUsers(Integer page, Integer size) {
        page = Optional.ofNullable(page).orElse(0);
        size = Optional.ofNullable(size).orElse(10);
        Pageable paging = PageRequest.of(page, size);
        Page<User> allUsers = userRepo.findAll(paging);
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : allUsers) {
            userDtoList.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getDateOfBirth(), user.getEmployeeId(), user.getBloodGroup(), user.getGender()));
        }
        return userDtoList;
    }

    //Changing the postedByUser to return String and not UserDto to check feign. changes 5
    @Override
    public UserDto getUserDetails(String id) {

        User userInfo = userRepo.findById(id).get();


//        UserDto userDtoInfo = new UserDto();
//        userDtoInfo.setId(userInfo.getId());
//        userDtoInfo.setFirstName(userInfo.getFirstName());
//        userDtoInfo.setLastName(userInfo.getLastName());
//        userDtoInfo.setMiddleName(userInfo.getMiddleName());
//        userDtoInfo.setPhoneNumber(userInfo.getPhoneNumber());
//        userDtoInfo.setEmail(userInfo.getEmail());
//        userDtoInfo.setAddress(userInfo.getAddress());
//        userDtoInfo.setBloodGroup(userInfo.getBloodGroup());
//        userDtoInfo.setEmployeeId(userInfo.getEmployeeId());
//        userDtoInfo.setGender(userInfo.getGender());
//        userDtoInfo.setDateOfBirth(userInfo.getDateOfBirth());
         UserDto userDto = new UserDto(userInfo.getId(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getMiddleName(), userInfo.getPhoneNumber(), userInfo.getEmail(), userInfo.getAddress(), userInfo.getDateOfBirth(), userInfo.getEmployeeId(), userInfo.getBloodGroup(), userInfo.getGender());
         System.out.println("The user info is:"+userDto);
         return userDto;

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

    @Override
    public UserDto getUserDetailsByEmail(String emailId) {
        Optional<User> user = userRepo.findByEmail(emailId);

        if (user.isPresent()) {
            User userDetail = user.get();
            return new UserDto(userDetail.getId(), userDetail.getFirstName(), userDetail.getLastName(), userDetail.getMiddleName(), userDetail.getPhoneNumber(), userDetail.getEmail(),userDetail.getAddress(),userDetail.getDateOfBirth(), userDetail.getEmployeeId(), userDetail.getBloodGroup(), userDetail.getGender());
        }
        else{
            throw new UserNotFoundException(USERNOTFOUND + emailId);
        }
    }

//    @Override
//    public String testFeign(String userId) {
//        return userRepo.findById(userId).get().getId();
//
//    }

//    @Override
//    public UserDto getUserDetailsByEmail(String emailId) {
//        return null;
//    }

}