package com.money.transfer.service;

import com.money.transfer.entity.User;
import com.money.transfer.repo.UserRepo;

import javax.transaction.Transactional;

public class Userservice {
       private UserRepo userRepo = new UserRepo();

    public void createUser(User userRequest)  {
        User user = User.builder().name(userRequest.getName()).email(userRequest.getEmail()).build();
        userRepo.saveOrUpdate(user);

    }

}
