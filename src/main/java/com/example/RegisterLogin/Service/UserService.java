package com.example.RegisterLogin.Service;


import com.example.RegisterLogin.Entity.Login;
import com.example.RegisterLogin.Entity.User;
import com.example.RegisterLogin.Repo.LoginRepo;

public interface UserService {

    String addUser(User user);
    String addAdmin(User user);
    LoginRepo loginUser(Login login);

}
