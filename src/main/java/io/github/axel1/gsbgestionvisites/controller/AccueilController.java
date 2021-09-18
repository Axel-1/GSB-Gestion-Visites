package io.github.axel1.gsbgestionvisites.controller;

import io.github.axel1.gsbgestionvisites.entity.Visiteur;
import io.github.axel1.gsbgestionvisites.service.MyUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AccueilController {
    @GetMapping(path = "")
    public String index(Model model, Authentication authentication) {
        MyUserPrincipal myUserPrincipal = (MyUserPrincipal) authentication.getPrincipal();
        Visiteur visiteur = myUserPrincipal.getVisiteur();

        model.addAttribute("title", "Accueil");
        return "index";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }
}
