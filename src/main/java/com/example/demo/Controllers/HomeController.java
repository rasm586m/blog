package com.example.demo.Controllers;

import com.example.demo.Services.BlogService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @GetMapping(path = "/")
    public String home(HttpSession session, Model model) throws SQLException, ClassNotFoundException {

        session.invalidate();

        userService.CreateUserTable();
        blogService.CreateBlogTable();

        model.addAttribute("bloglist", blogService.getAllBlogs());

        return "index.html";
    }

    @GetMapping(path = "/index")
    public String index(Model model) throws SQLException, ClassNotFoundException {

        model.addAttribute("bloglist", blogService.getAllBlogs());

        return "index.html";
    }


}
