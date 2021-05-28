package com.itsj.usermanager.validation.email;

import com.itsj.usermanager.entity.User;
import com.itsj.usermanager.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailExistedValidator {
    @Autowired
    private UserRepository userRepository;

    public boolean emailExists(String email) {
        boolean exists = false;

        if (email != null && !StringUtils.isBlank(email)) {
            User user = userRepository.findByEmail(email);
            exists = (user != null);
        }

        return exists;
    }
}
