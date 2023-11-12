package com.cbank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cbank.Model.User;
import com.cbank.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class login {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/logout")
    public String logout(HttpServletRequest session) {
        HttpSession request = session.getSession();
        request.removeAttribute("user");
        request.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String Clogin(Model model, HttpServletRequest session) {
        User user = (User) session.getSession().getAttribute("user");
        if (user != null) {
            return "redirect:/menu";
        } else {
            model.addAttribute("username", "");
            model.addAttribute("password", "");
            return "login";
        }
    }

    @PostMapping("/login")
    public String loginMethod(Model model, HttpServletRequest session, @RequestParam("username") String username,
            @RequestParam("password") String password) {
        User user = userRepo.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.getSession().setAttribute("user", user);
            return "redirect:/menu";
        } else {
            return "login";
        }
    }

}
