package com.itsj.usermanager.validation.email;

import com.itsj.usermanager.entity.User;
import com.itsj.usermanager.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserExistedValidator {
    @Autowired
    private UserRepository userRepository;

    public boolean userExists(String username) {
        boolean exists = false;

        if (username != null && !StringUtils.isBlank(username)) {
            User user = userRepository.findByUserName(username);
            exists = (user != null);
        }

        return exists;
    }
}
