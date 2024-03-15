package com.example.RegisterLogin.Service.IMPL;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.RegisterLogin.DAO.LoginDAO;
import com.example.RegisterLogin.DAO.UserDAO;
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
    public String addUser(UserDAO userDAO) {
        
        User user = new User(

            userDAO.getUserid(),
            userDAO.getUsername(),
            userDAO.getEmail(),
            this.passwordEncoder.encode(userDAO.getPassword())

        );
        userRepo.save(user);
        return "Register Success";
    }

    @Override
    public LoginRepo loginUser(LoginDAO loginDAO) {
       
        String msg="";
        User user1 = userRepo.findByEmail(loginDAO.getEmail());
        if(user1 != null){
            String password = loginDAO.getPassword();
            String encodedPassword= user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if(isPwdRight){
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDAO.getEmail(),encodedPassword);
                if (user.isPresent()){
                    return new LoginRepo("Login Succes",true);
                }else {
                    return new LoginRepo("Login Failed",false);
                }
            }else{
                return new LoginRepo("Password not match",false);
            }
        }else{
            return new LoginRepo("Email not exits",false);
        }
    }

}
