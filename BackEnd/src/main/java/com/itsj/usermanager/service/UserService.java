package com.itsj.usermanager.service;

import com.itsj.usermanager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> getAll();
    public void deleteUser(String username);
    public List<User> getUserByUsername(String username);
    public List<User> getUserByFirstname(String firstname);
    public List<User> getUserByLastname(String lastname);
    public List<User> getUserByEmail(String email);
}
