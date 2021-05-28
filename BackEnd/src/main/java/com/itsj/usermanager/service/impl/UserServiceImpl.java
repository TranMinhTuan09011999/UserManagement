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

}
