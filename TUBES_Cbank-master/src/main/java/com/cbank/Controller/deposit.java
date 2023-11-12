package com.cbank.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cbank.Model.User;
import com.cbank.Repository.AccountRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller

public class deposit {

    @Autowired
    AccountRepository accountRepo;

    @GetMapping("/deposit")
    public String Cdeposit(Model model, HttpServletRequest session) {
        User user = (User) session.getSession().getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "deposit";
        } else {
            return "login";
        }
    }

    @PostMapping("/doDeposit")
    public String doDeposit(Model model, HttpServletRequest session, @RequestParam("newDeposit") String newDeposit) {
        User user = (User) session.getSession().getAttribute("user");
        System.out.println(newDeposit);

        if (user != null) {
            if (!newDeposit.equals("")) {
                double amount = Double.parseDouble(newDeposit);

                user.getAccount().deposit(amount);
                accountRepo.save(user.getAccount());
                return "redirect:/deposit";
            }
            return "redirect:/deposit";

        } else {
            return "login";
        }
    }
}