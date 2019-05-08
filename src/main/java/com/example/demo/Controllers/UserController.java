package com.example.demo.Controllers;


import com.example.demo.Models.User;
import com.example.demo.Services.BlogService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @PostMapping("/login")
    public String login(Model model, HttpSession session, WebRequest wr) throws SQLException, ClassNotFoundException {

        String username = wr.getParameter("username");
        String password = wr.getParameter("password");

        User user = userService.getUsername(username);

        if (userService.checkIfAdmin(username, password)) {
            session.setAttribute("user", user);
            model.addAttribute("bloglist", blogService.getAllBlogs());
            return "index.html";

        } else {
            model.addAttribute("bloglist", blogService.getAllBlogs());
            return "index.html";
        }

    }


}

