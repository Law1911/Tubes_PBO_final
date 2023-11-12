package com.cbank.Controller;

import com.cbank.Repository.RecipientRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class deleteRecipient {

    @Autowired
    private RecipientRepository recipientRepository;

    @PostMapping ("/deleteRecipient/{id}")
    public String CdeleteRecipient(Model model, HttpServletRequest request, @PathVariable("id") Integer id){
        recipientRepository.deleteById(id);

        return "redirect:/menu";
    }

}
