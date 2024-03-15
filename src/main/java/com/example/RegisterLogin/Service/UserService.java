package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.DAO.LoginDAO;
import com.example.RegisterLogin.DAO.UserDAO;
import com.example.RegisterLogin.Repo.LoginRepo;

public interface UserService {

    String addUser(UserDAO userDAO);
    LoginRepo loginUser(LoginDAO loginDAO);

}
