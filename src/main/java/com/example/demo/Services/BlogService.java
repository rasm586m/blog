package com.example.demo.Services;

import com.example.demo.Models.Blog;
import com.example.demo.Models.User;
import com.example.demo.Repository.BlogRepo;
import com.example.demo.Repository.CreateTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private CreateTables createTables;
    @Autowired
    private BlogRepo blogRepo;

    public void CreateBlogTable() throws SQLException, ClassNotFoundException {

        createTables.createBlog();
    }

    public List<Blog> getAllBlogs() throws SQLException, ClassNotFoundException {

        return blogRepo.getAlleBlogs();
    }

    public void createBlogPost(String title, String text, User username) throws SQLException, ClassNotFoundException {

        blogRepo.createBlogPost(title, text, username);
    }

    public void deleteBlogPost(int id) throws SQLException, ClassNotFoundException {

        blogRepo.deleteBlogPost(id);
    }

    public Blog getBlogPost(int id) throws SQLException, ClassNotFoundException {

        return blogRepo.getBlogPost(id);
    }

    public void updateBlog(int id, String title, String text) throws SQLException, ClassNotFoundException {

        blogRepo.updateBlogPost(id, title, text);

    }
}
