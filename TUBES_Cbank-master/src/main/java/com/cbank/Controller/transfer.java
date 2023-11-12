package com.cbank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cbank.Model.Account;
import com.cbank.Model.Recipient;
import com.cbank.Model.User;
import com.cbank.Repository.AccountRepository;
import com.cbank.Repository.RecipientRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class transfer {

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    RecipientRepository recipientRepo;

    @GetMapping("/transfer")
    public String Ctrasfer(Model model, HttpServletRequest session) {
        User user = (User) session.getSession().getAttribute("user");

        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("norekTujuan", "");
            return "transfer";
        } else {
            return "login";
        }
    }

    @PostMapping("/toNextTransfer")
    public String toNextTransfer(Model model, HttpServletRequest session,
            @RequestParam("norekTujuan") String norekTujuan) {
        User user = (User) session.getSession().getAttribute("user");

        if (user != null) {
            model.addAttribute("user", user);
            if (!norekTujuan.equals("")) {
                Account accountTujuan = accountRepo.findByNorek(norekTujuan);
                if (accountTujuan != null) {
                    model.addAttribute("nominal", (double) 0);
                    model.addAttribute("norekTujuan", "");
                    model.addAttribute("accountTujuan", accountTujuan);
                    return "transferNext";
                } else {
                    return "transfer";
                }
            }
            return "transfer";

        } else {
            return "login";
        }
    }

    @PostMapping("/doTransfer")
    public String doTransfer(Model model, HttpServletRequest session, @RequestParam("nominal") double nominal,
            @RequestParam("norekTujuan") String norekTujuan) {
        User user = (User) session.getSession().getAttribute("user");
        System.out.println("nominal : " + nominal);
        System.out.println("Norek tujuan : " + norekTujuan);
        if (user != null) {
            Account accountTujuan = accountRepo.findByNorek(norekTujuan);

            List<Recipient> recipientsListUser = recipientRepo.findByAccount_id(user.getAccount().getId());
            boolean sudahAdaRecipient = false;
            for (Recipient recipient : recipientsListUser) {
                if (recipient.getNorek() == norekTujuan) {
                    sudahAdaRecipient = true;
                    break;
                }
            }

            if (!sudahAdaRecipient) {
                Recipient newRecipient = new Recipient(norekTujuan, accountTujuan.getFirstName(),
                        accountTujuan.getLastName(), user.getAccount());
                user.getAccount().getRecipients().add(newRecipient);
            }

            if (accountTujuan != null) {
                Account updateAccountTujuan = user.getAccount().transfer(nominal, accountTujuan);

                // kalo uang nya kurang, updateAccountTujuan akan null, jika uangnya cukup maka
                // return account penerima
                if (updateAccountTujuan != null) {
                    // update akun penerima ke database
                    accountRepo.save(updateAccountTujuan);
                    updateAccountTujuan.resetTransactions();

                    // update akun pengirim (yang login saat ini)
                    accountRepo.save(user.getAccount());
                    user.getAccount().resetRecipients();
                    user.getAccount().resetTransactions();
                    return "redirect:/transfer";
                } else {
                    return "transfer";
                }
            } else {
                return "transfer";
            }
        } else {
            return "login";
        }
    }

}
