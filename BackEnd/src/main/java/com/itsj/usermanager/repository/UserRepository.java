package com.itsj.usermanager.repository;

import com.itsj.usermanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUserName(String username);
    User findByEmail(String email);
}
