package com.cbank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cbank.Model.Transaction;
import com.cbank.Model.User;
import com.cbank.Repository.TransactionRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller
public class printTransactionHistory {

    @Autowired
    TransactionRepository transactionRepo;

    @GetMapping("/printTransactionHistory")
    public String CprintTransactionHistory(Model model, HttpServletRequest session) {
        User user = (User) session.getSession().getAttribute("user");
        if (user != null) {
            List<Transaction> transactions = transactionRepo.findByAccount_id(user.getAccount().getId());
            System.out.println(transactions);
            model.addAttribute("transactionHistory", transactions);
            return "printTransactionHistory";
        } else {
            return "login";
        }
    }
}
