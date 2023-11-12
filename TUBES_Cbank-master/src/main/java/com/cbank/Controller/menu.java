package com.cbank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cbank.Model.Recipient;
import com.cbank.Model.User;
import com.cbank.Repository.RecipientRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class menu {

    @Autowired
    RecipientRepository recipientRepository;

    @GetMapping("/menu")
    public String Cmenu(Model model, HttpServletRequest session) {
        User user = (User) session.getSession().getAttribute("user");

        if (user != null) {
            model.addAttribute("user", user);
            List<Recipient> recipients = recipientRepository.findByAccount_id(user.getAccount().getId());
            model.addAttribute("listRecipients", recipients);
            return "menu";
        } else {
            return "redirect:/login";
        }
    }
}
