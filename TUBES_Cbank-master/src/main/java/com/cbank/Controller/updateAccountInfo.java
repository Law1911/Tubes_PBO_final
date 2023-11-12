package com.cbank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cbank.Model.User;
import com.cbank.Repository.AccountRepository;
import com.cbank.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller
public class updateAccountInfo {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/updateAccountInfo")
    public String CupdateAccountInfo(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("firstName", "");
            model.addAttribute("lastName", "");
            model.addAttribute("password", "");
            return "updateAccountInfo";
        } else {
            return "login";

        }

    }

    @PostMapping("/doUpdate")
    public String doUpdateAccountInfo(Model model, HttpServletRequest request,
            @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
            @RequestParam("password") String password) {
        System.out.println(firstName + " " + lastName + " " + password);
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (firstName != "" && lastName != "") {
                System.out.println(user.getPassword());
                if (password.equals(user.getPassword())) {
                    user.getAccount().setFirstName(firstName);
                    user.getAccount().setLastName(lastName);
                    user.setPassword(password);
                    userRepo.save(user);
                    accountRepo.save(user.getAccount());
                    return "redirect:/updateAccountInfo";
                } else {
                    System.out.println("ke masuk sini");
                    return "updateAccountInfo";
                }
            } else {
                System.out.println("masuk sini");
                return "updateAccountInfo";
            }
        } else {
            return "login";
        }

    }
}
