package com.example.RegisterLogin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterLogin.Entity.Login;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Repo.LoginRepo;
import com.example.RegisterLogin.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService  userService;

    @PostMapping(path = "/register/user")
    public String  registerUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping(path = "/register/admin")
    public String registerAdmin(@RequestBody User user) {
        return userService.addAdmin(user);
    }

    @PostMapping(path ="/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login)
    {
        LoginRepo loginRepo= userService.loginUser(login);
        return ResponseEntity.ok(loginRepo);
    }

}
