package com.example.demo.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;

@Service
public class CreateTables {

    @Autowired
    private DBConnector database;

    public void createUsers() throws SQLException, ClassNotFoundException {

        Statement stmt = database.connect().createStatement();

        String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id int PRIMARY KEY AUTO_INCREMENT," +
                "role varchar(10)," +
                "username varchar(20)," +
                "password varchar(50)" +
                ");";
        stmt.execute(userTable);
        database.disconnect();
    }

    public void createBlog() throws SQLException, ClassNotFoundException {

        Statement stmt = database.connect().createStatement();

        String blogTable = "CREATE TABLE IF NOT EXISTS blog (" +
                "id int PRIMARY KEY AUTO_INCREMENT," +
                "title varchar(100)," +
                "text longtext," +
                "username varchar(20)" +
                ");";
        stmt.execute(blogTable);
        database.disconnect();
    }


}

