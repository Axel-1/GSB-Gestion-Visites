package io.github.axel1.gsbgestionvisites.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    private final RapportController rapportController;

    @Autowired
    public TestController(RapportController rapportController) {
        this.rapportController = rapportController;
    }

    @GetMapping
    public String test(Model model) {
        return "test";
    }
}
