package com.example.demo.Services;

import com.example.demo.Models.User;
import com.example.demo.Repository.CreateTables;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CreateTables createTables;
    @Autowired
    private UserRepo userRepo;

    public void CreateUserTable() throws SQLException, ClassNotFoundException {

        createTables.createUsers();
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {

        return userRepo.getUsers();
    }

    public boolean checkIfAdmin(String username, String password) throws SQLException, ClassNotFoundException {

        return userRepo.verifyUser(username, password);

    }

    public User getUsername(String username) throws SQLException, ClassNotFoundException {

        return userRepo.getUser(username);
    }


}
