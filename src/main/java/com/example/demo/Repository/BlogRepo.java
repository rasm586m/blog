package com.example.demo.Repository;

import com.example.demo.Models.Blog;
import com.example.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogRepo {

    @Autowired
    private DBConnector blogDatabase;

    public void createBlogPost(String title, String text, User username) throws SQLException, ClassNotFoundException {

        Statement statement = blogDatabase.connect().createStatement();

        String blogPost = "INSERT INTO blog (title, text, username) VALUES ('" +
                title + "','" +
                text + "','" +
                username.getUsername() +
                "');";

        statement.execute(blogPost);
    }

    public void deleteBlogPost(int id) throws SQLException, ClassNotFoundException {

        Statement statement = blogDatabase.connect().createStatement();

        String deleteBlogPost = "DELETE FROM blog WHERE id='" + id + "';";

        statement.execute(deleteBlogPost);
    }

    public List<Blog> getAlleBlogs() throws SQLException, ClassNotFoundException {

        Statement statement = blogDatabase.connect().createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM blog");

        List<Blog> blogList = new ArrayList<>();

        while (resultSet.next()) {
            Blog blog = new Blog();
            blog.setId(resultSet.getInt("id"));
            blog.setTitle(resultSet.getString("title"));
            blog.setText(resultSet.getString("text"));
            blog.setUsername(resultSet.getString("username"));
            blogList.add(blog);
        }
        return blogList;


    }


}

