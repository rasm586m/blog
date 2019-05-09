package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/editblog/{id}")
    public String editBlog(Model model, @PathVariable int id, HttpSession session) throws SQLException, ClassNotFoundException {

        model.addAttribute("blog", blogService.getBlogPost(id));
        session.setAttribute("edit",true);

        return "blog.html";
    }

    @PutMapping(path = "/saveEdit/{id]")
    public String saveBlog(Model model, @PathVariable int id, @RequestParam String title, @RequestParam String text) throws SQLException, ClassNotFoundException {

        model.addAttribute("blog", blogService.getBlogPost(id));
        blogService.updateBlog(id, title, text);

        model.addAttribute("bloglist", blogService.getAllBlogs());
        return "index.html";

    }
}
