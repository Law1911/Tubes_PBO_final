package com.cbank.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class listCustomers {
    @GetMapping("/listCustomers")
    public String ClistCustomers(Model model) {
        return "listCustomers";
    }
}
