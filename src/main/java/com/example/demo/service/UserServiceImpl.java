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
    public UserDto createUser(UserRequest userRequest) {
        User newUser = new User();
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setMiddleName(userRequest.getMiddleName());
        newUser.setPhoneNumber(userRequest.getPhoneNumber());
        if (userRepo.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException(EMAILALREADYEXIST);
        } else {
            newUser.setEmail(userRequest.getEmail());
        }
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
        UserDto userDto = new UserDto(userInfo.getId(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getMiddleName(), userInfo.getPhoneNumber(), userInfo.getEmail(), userInfo.getAddress(), userInfo.getDateOfBirth(), userInfo.getEmployeeId(), userInfo.getBloodGroup(), userInfo.getGender());
        log.info("The user details are"+userDto);
        return userDto;
    }

    @Override
    public UserDto getUserDetailsByEmail(String emailId) {
        Optional<User> user = userRepo.findByEmail(emailId);
        if (user.isPresent()) {
            User userDetails = user.get();
            return new UserDto(userDetails.getId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getMiddleName(), userDetails.getPhoneNumber(), userDetails.getEmail(), userDetails.getAddress(), userDetails.getDateOfBirth(), userDetails.getEmployeeId(), userDetails.getBloodGroup(), userDetails.getGender());
        } else
            throw new UserNotFoundException(NOUSERFOUND);
    }

    @Override
    public UserDto updateUser(UserRequest userRequest, String userId) {
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
            return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getPhoneNumber(), user.getEmail(), user.getAddress(), user.getDateOfBirth(), user.getEmployeeId(), user.getBloodGroup(), user.getGender());
        } else {
            throw new UserNotFoundException(USERNOTFOUND + userId);
        }
    }

    @Override
    public String deleteUser(String id) {
        try {
            Optional<User> userToBeDeleted = userRepo.findById(id);
            if(userToBeDeleted.isPresent()) {
                userRepo.deleteById(userToBeDeleted.get().getId());
            }
            return DELETEUSER;
        } catch (Exception e) {
            throw new UserNotFoundException(USERNOTFOUND + id);
        }
    }
}


