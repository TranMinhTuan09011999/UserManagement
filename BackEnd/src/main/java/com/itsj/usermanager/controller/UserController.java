package com.itsj.usermanager.controller;

import com.google.common.hash.Hashing;
import com.itsj.usermanager.entity.AuthRequest;
import com.itsj.usermanager.entity.User;
import com.itsj.usermanager.exception.UserAlreadyExistedException;
import com.itsj.usermanager.repository.UserRepository;
import com.itsj.usermanager.service.EmailService;
import com.itsj.usermanager.service.UserService;
import com.itsj.usermanager.util.JwtUtil;
import com.itsj.usermanager.validation.email.EmailExistedValidator;
import com.itsj.usermanager.validation.email.UserExistedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailExistedValidator emailExistedValidator;

    @Autowired
    private UserExistedValidator userExistedValidator;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @GetMapping("/users")
    public ResponseEntity<?> getALLUser() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @DeleteMapping("/users/delete")
    public void deleteStudent(@Param(value = "username") String username) {
        userService.deleteUser(username);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
        String username = user.getUserName();
        String email = user.getEmail();
        System.out.println(username);
        Optional<User> user1 = Optional.ofNullable(userRepository.findByUserName(username));
        if (user1.isPresent()) {
            throw new UserAlreadyExistedException("User has been already existed!!!");
        }
        Optional<User> user2 = Optional.ofNullable(userRepository.findByEmail(email));
        if (user2.isPresent()) {
            throw new UserAlreadyExistedException("Email has been already existed!!!");
        }

        boolean result = emailService.sendEmail("User Registration","Successlly",user.getEmail());
        if(result){
            //System.out.println(encoder.encode(user.getPassword()));
            // Create new user's account
            User newUser = new User(user.getUserName(),
                    encoder.encode(user.getPassword()),
                    user.getLastname(),
                    user.getFirstname(),
                    user.getEmail());
            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
        }else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sending fail...");
        }
    }

    @PostMapping("/emailcheck")
    public ResponseEntity<?> emailCheck(@RequestBody Map<String, Object> inputData) {
        String email = (String)inputData.get("email");

        Boolean bool = emailExistedValidator.emailExists(email);

        return ResponseEntity.status(HttpStatus.OK).body(bool);
    }
    @PostMapping("/usernamecheck")
    public ResponseEntity<?> usernameCheck(@RequestBody Map<String, Object> inputData) {
        String username = (String)inputData.get("userName");

        Boolean bool = userExistedValidator.userExists(username);

        return ResponseEntity.status(HttpStatus.OK).body(bool);
    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<?> getALLUserByUsername(@PathVariable String username) {
        List<User> users = userService.getUserByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/firstname/{firstname}")
    public ResponseEntity<?> getALLUserByFirstname(@PathVariable String firstname) {
        List<User> users = userService.getUserByFirstname(firstname);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/lastname/{lastname}")
    public ResponseEntity<?> getALLUserByLastname(@PathVariable String lastname) {
        List<User> users = userService.getUserByLastname(lastname);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<?> getALLUserByEmail(@PathVariable String email) {
        List<User> users = userService.getUserByUsername(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public String sha256(String password){
        return Hashing.sha256().hashString("your input", StandardCharsets.UTF_8)
                .toString();
    }
}
