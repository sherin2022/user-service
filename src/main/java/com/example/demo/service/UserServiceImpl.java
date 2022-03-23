package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepo userRepo;



    public User createUser(User user) {

        return userRepo.save(user);
       // return userRepo.save(new User(user.getUserId(),user.getFirstName(),user.getMiddleName(),user.getLastName(),user.getPhoneNumber(),user.getDateOfBirth(),user.getGender(),user.getMartialStatus(),user.getEmployeeNumber(),user.getBloodGroup(),user.getEmail(),user.getPassword()));

    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserDetails(String id) {
        return userRepo.findByUserId(id);
    }

    @Override
    public User updateUser(User user,String userId) {
        User userToBeUpdated = userRepo.findByUserId(userId);
        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setMiddleName(user.getMiddleName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setGender(user.getGender());
        userToBeUpdated.setBloodGroup(user.getBloodGroup());
        userToBeUpdated.setBloodGroup(user.getDateOfBirth());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setPhoneNumber(user.getPhoneNumber());
        return userRepo.save(userToBeUpdated);

    }


    @Override
    public User deleteUser(String id) {
        User userToBeDeleted = userRepo.findByUserId(id);
        return userRepo.deleteByUserId(userToBeDeleted.getUserId());
    }


}
