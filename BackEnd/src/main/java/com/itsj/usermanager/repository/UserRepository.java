package com.itsj.usermanager.repository;

import com.itsj.usermanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUserName(String username);
    User findByEmail(String email);

    List<User> findByUserNameContaining(String email);
    List<User> findByFirstnameContaining(String email);
    List<User> findByLastnameContaining(String email);
    List<User> findByEmailContaining(String email);

}
