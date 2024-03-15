package com.example.RegisterLogin.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterLogin.DAO.LoginDAO;
import com.example.RegisterLogin.DAO.UserDAO;
import com.example.RegisterLogin.Repo.LoginRepo;
import com.example.RegisterLogin.Service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService  userService;

    @PostMapping(path = "/register")
    public String  registerUser(@RequestBody UserDAO userDAO) {

        String id= userService.addUser(userDAO);
        return id;
    }

    @PostMapping(path ="/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDAO loginDAO)
    {
        LoginRepo loginRepo= userService.loginUser(loginDAO);
        return ResponseEntity.ok(loginRepo);
    }

}
