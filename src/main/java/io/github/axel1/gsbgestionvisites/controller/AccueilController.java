package io.github.axel1.gsbgestionvisites.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccueilController {

    @GetMapping(path = "")
    public String index(Model model) {
        model.addAttribute("title", "Accueil");
        return "index";
    }

    @GetMapping(path = "/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }

        model.addAttribute("title", "Connexion");
        return "login";
    }
}
