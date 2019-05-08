package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping(path = "/blog")
    public String blog() {

        return "blog.html";
    }

    @PostMapping(path = "/createblog")
    public String createBlog(Model model, HttpSession session, @RequestParam String title, @RequestParam String text) throws SQLException, ClassNotFoundException {

        User user = (User) session.getAttribute("user");

        blogService.createBlogPost(title, text, user);

        model.addAttribute("bloglist", blogService.getAllBlogs());

        return "index.html";
    }

    @GetMapping(path = "/deleteblog/{id}")
    public String deleteBlog(Model model, @PathVariable int id) throws SQLException, ClassNotFoundException {

        blogService.deleteBlogPost(id);

        model.addAttribute("bloglist", blogService.getAllBlogs());
        return "index.html";
    }

    @PostMapping(path = "/delblog")
    public String delBlog(Model model, @RequestParam int id) throws SQLException, ClassNotFoundException {

        blogService.deleteBlogPost(id);
        model.addAttribute("bloglist", blogService.getAllBlogs());
        return "index.html";
    }

}
