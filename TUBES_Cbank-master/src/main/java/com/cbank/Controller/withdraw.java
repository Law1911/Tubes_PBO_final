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
public class withdraw {

    @Autowired
    AccountRepository accountRepo;

    @GetMapping("/withdraw")
    public String Cdeposit(Model model, HttpServletRequest session) {
        User user = (User) session.getSession().getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "withdraw";
        } else {
            return "login";
        }
    }

    @PostMapping("/doWithdraw")
    public String doDeposit(Model model, HttpServletRequest session, @RequestParam("newDeposit") String amount) {
        User user = (User) session.getSession().getAttribute("user");

        if (user != null) {
            if (!amount.equals("")) {
                double newWithdraw = Double.parseDouble(amount);

                if (user.getAccount().getBalance() > newWithdraw) {
                    user.getAccount().withdraw(newWithdraw);
                    accountRepo.save(user.getAccount());
                    return "redirect:/withdraw";
                }
            }
            return "redirect:/withdraw";
        } else {
            return "login";
        }
    }
}
