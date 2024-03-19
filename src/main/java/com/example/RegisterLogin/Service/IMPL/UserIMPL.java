package com.example.RegisterLogin.Service.IMPL;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.RegisterLogin.Entity.Login;
import com.example.RegisterLogin.Entity.Role;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Repo.LoginRepo;
import com.example.RegisterLogin.Repo.UserRepo;
import com.example.RegisterLogin.Service.UserService;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User user1) {
    if (userRepo.existsByUsername(user1.getUsername())) {
        return "Username already exists";
    }

    if (userRepo.existsByEmail(user1.getEmail())) {
        return "Email already exists";
    }
    User user = new User(
        user1.getUserid(),
        user1.getUsername(),
        user1.getEmail(),
        this.passwordEncoder.encode(user1.getPassword()),
        Role.USER
        );
        userRepo.save(user);
        return "Register User Success";
    }
    @Override
    public String addAdmin(User userDAO) {
    if (userRepo.existsByUsername(userDAO.getUsername())) {
        return "Username already exists";
    }

    if (userRepo.existsByEmail(userDAO.getEmail())) {
        return "Email already exists";
    }
    User user = new User(
        userDAO.getUserid(),
        userDAO.getUsername(),
        userDAO.getEmail(),
        this.passwordEncoder.encode(userDAO.getPassword()),
        Role.ADMIN
    );
    userRepo.save(user);
    return "Register Admin Success";
}

    @Override
    public LoginRepo loginUser(Login login) {
       
        String msg="";
        User user1 = userRepo.findByEmail(login.getEmail());
        if(user1 != null){
            String password = login.getPassword();
            String encodedPassword= user1.getPassword();
            Role role = user1.getRole();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if(isPwdRight){
                Optional<User> user = userRepo.findOneByEmailAndPassword(login.getEmail(),encodedPassword);
                if (user.isPresent()){
                    return new LoginRepo("Login Succes",true,role);
                }else {
                    return new LoginRepo("Login Failed",false,null);
                }
            }else{
                return new LoginRepo("Password not match",false,null);
            }
        }else{
            return new LoginRepo("Email not exits",false,null);
        }
    }

}
