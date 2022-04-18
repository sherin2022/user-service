package com.example.demo.service;
import com.example.demo.dto.UserWithoutPassword;
import com.example.demo.dto.UserRequest;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.exception.EmailAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.example.demo.constantException.UserConstantException.*;

@Service
public class UserServiceImpl implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepo userRepo;


    @Override
    public UserWithoutPassword createUser(UserRequest userRequest) {

        if (userRepo.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException(EMAILALREADYEXIST);
        }
        User newUser = new User();
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setMiddleName(userRequest.getMiddleName());
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        newUser.setAddress(userRequest.getAddress());
        newUser.setEmail(userRequest.getEmail());
        newUser.setDateOfBirth(userRequest.getDateOfBirth());
        newUser.setGender(userRequest.getGender());
        newUser.setEmployeeId(userRequest.getEmployeeId());
        newUser.setBloodGroup(userRequest.getBloodGroup());
        newUser.setPassword(userRequest.getPassword());
        newUser = userRepo.save(newUser);
        return new UserWithoutPassword(newUser.getId(), newUser.getFirstName(), newUser.getLastName(), newUser.getMiddleName(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getAddress(), newUser.getDateOfBirth(), newUser.getEmployeeId(), newUser.getBloodGroup(), newUser.getGender());
    }

    @Override
    public List<UserWithoutPassword> getUsers(Integer page, Integer pageSize) {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<User> allUsers = userRepo.findAll(PageRequest.of(page - 1, pageSize));
        List<UserWithoutPassword> userDtoList = new ArrayList<>();
        for (User user : allUsers) {
            userDtoList.add(new UserWithoutPassword(user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getDateOfBirth(), user.getEmployeeId(), user.getBloodGroup(), user.getGender()));
        }
        if (userDtoList.isEmpty()) {
            throw new UserNotFoundException(NOUSERFOUND);
        }
        return userDtoList;
    }

    //Changing the postedByUser to return String and not UserDto to check feign. changes 5
    @Override
    public UserWithoutPassword getUserDetails(String id) {

        User userInfo = userRepo.findById(id).get();
        UserWithoutPassword userDto = new UserWithoutPassword(userInfo.getId(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getMiddleName(), userInfo.getPhoneNumber(), userInfo.getEmail(), userInfo.getAddress(), userInfo.getDateOfBirth(), userInfo.getEmployeeId(), userInfo.getBloodGroup(), userInfo.getGender());
        log.info("The user details are %s",userDto);
        return userDto;
    }

    @Override
    public UserWithoutPassword getUserDetailsByEmail(String emailId) {
        Optional<User> user = userRepo.findByEmail(emailId);
        if (user.isPresent()) {
            User userDetails = user.get();
            return new UserWithoutPassword(userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getMiddleName(), userDetails.getPhoneNumber(), userDetails.getEmail(), userDetails.getAddress(), userDetails.getDateOfBirth(), userDetails.getEmployeeId(), userDetails.getBloodGroup(), userDetails.getGender());
        } else
            throw new UserNotFoundException(NOUSERFOUND);
    }

    @Override
    public UserWithoutPassword updateUser(UserRequest userRequest, String userId) {
        Optional<User> userToBeUpdated = userRepo.findById(userId);
        if (userToBeUpdated.isPresent()) {
            User user = userToBeUpdated.get();
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
            return new UserWithoutPassword(user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getDateOfBirth(), user.getEmployeeId(), user.getBloodGroup(), user.getGender());
        } else {
            throw new UserNotFoundException(USERNOTFOUND + userId);
        }
    }

    @Override
    public String deleteUser(String userId) {
        try {
            userRepo.deleteById(userId);
            return DELETEUSER;
        } catch (Exception e) {
            throw new UserNotFoundException(USERNOTFOUND + userId);
        }
    }
}


