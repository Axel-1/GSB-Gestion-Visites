package io.github.axel1.gsbgestionvisites.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @GetMapping(path = "")
    public String index(Model model) {
        model.addAttribute("title", "Accueil");
        return "index";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login";
    }
}
