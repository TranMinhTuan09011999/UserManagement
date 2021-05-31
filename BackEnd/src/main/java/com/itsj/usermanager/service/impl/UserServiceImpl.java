package com.itsj.usermanager.service.impl;

import com.itsj.usermanager.entity.User;
import com.itsj.usermanager.repository.UserRepository;
import com.itsj.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public List<User> getUserByUsername(String username) {
        return userRepository.findByUserNameContaining(username);
    }

    @Override
    public List<User> getUserByFirstname(String firstname) {
        return userRepository.findByFirstnameContaining(firstname);
    }

    @Override
    public List<User> getUserByLastname(String lastname) {
        return userRepository.findByLastnameContaining(lastname);
    }

    @Override
    public List<User> getUserByEmail(String email) {
        return userRepository.findByEmailContaining(email);
    }
}
