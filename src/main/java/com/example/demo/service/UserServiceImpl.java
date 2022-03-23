package com.example.demo.service;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRequest;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.constantException.UserConstantException.*;

@Service
public class UserServiceImpl implements UserService
{
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepo userRepo;



    public UserDTO createUser(UserRequest userRequest) {

        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setMiddleName(userRequest.getMiddleName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEmail(userRequest.getEmail());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setGender(userRequest.getGender());
        user.setEmployeeNumber(userRequest.getEmployeeNumber());
        user.setBloodGroup(userRequest.getBloodGroup());
        user.setPassword(userRequest.getPassword());
        user = userRepo.save(user);
        

        return new UserDTO(user.getUserId(), user.getFirstName(),user.getLastName(),user.getMiddleName(),user.getPhoneNumber(),user.getEmail(),user.getDateOfBirth(),user.getEmployeeNumber(),user.getBloodGroup(),user.getGender());


    }

    @Override
    public List<UserDTO> getUsers() {
       List<User> users=userRepo.findAll();

            List<UserDTO> userDtoList = new ArrayList<>();
            for (User user1 : users) {
                userDtoList.add(new UserDTO(user1.getUserId(), user1.getFirstName(), user1.getLastName(), user1.getMiddleName(), user1.getPhoneNumber(), user1.getEmail(), user1.getDateOfBirth(), user1.getEmployeeNumber(), user1.getBloodGroup(), user1.getGender()));
            }
            if(userDtoList.isEmpty()){
                throw new UserNotFoundException(NOUSERFOUND);
            }
            return userDtoList;
    }

    public UserDTO getUserDetails(String id) {
    Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
    User user1 = user.get();
    return new UserDTO(user1.getUserId(), user1.getFirstName(), user1.getLastName(), user1.getMiddleName(), user1.getPhoneNumber(), user1.getEmail(), user1.getDateOfBirth(), user1.getEmployeeNumber(), user1.getBloodGroup(), user1.getGender());
}
        else{
    throw new UserNotFoundException(USERNOTFOUND + id);
}
}
    @Override
    public UserDTO updateUser(UserRequest userRequest,String userId) {
        Optional<User> user2 = userRepo.findById(userId);
        if(user2.isPresent()) {
            User user = user2.get();
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setMiddleName(userRequest.getMiddleName());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setEmail(userRequest.getEmail());
            user.setDateOfBirth(userRequest.getDateOfBirth());
            user.setGender(userRequest.getGender());
            user.setBloodGroup(userRequest.getBloodGroup());
            user.setPassword(userRequest.getPassword());
            userRepo.save(user);
            return new UserDTO(user.getUserId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getPhoneNumber(), user.getEmail(), user.getDateOfBirth(), user.getEmployeeNumber(), user.getBloodGroup(), user.getGender());
        }
        else{
            throw new UserNotFoundException(USERNOTFOUND + userId);
        }

    }


    @Override
    public String deleteUser(String id) {
        try {
            User userToBeDeleted = userRepo.findByUserId(id);
             userRepo.deleteByUserId(userToBeDeleted.getUserId());
            return DELETEUSER;
        }
        catch (Exception e){
            throw new UserNotFoundException(USERNOTFOUND + id);
        }
    }


}
