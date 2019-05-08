package com.example.demo.Repository;

import com.example.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepo {

    @Autowired
    private DBConnector blogDatabase;

    public List<User> getUsers() throws SQLException, ClassNotFoundException {

        Statement statement = blogDatabase.connect().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

        List<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setRole(resultSet.getString("role"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getInt("password"));
            userList.add(user);
        }
        return userList;
    }

    public User getUser(String username) throws SQLException, ClassNotFoundException {

        Statement stmt = blogDatabase.connect().createStatement();

        ResultSet resultSet = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "';");

        User user = new User();

        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setRole(resultSet.getString("role"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getInt("password"));
        }

        return user;
    }

    public boolean verifyUser(String username, String password) throws SQLException, ClassNotFoundException {

        Statement statement = blogDatabase.connect().createStatement();

        String input = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "';";

        ResultSet resultSet = statement.executeQuery(input);

        if (!resultSet.next()) {
            return false;
        } else {
            return true;
        }
    }


}
