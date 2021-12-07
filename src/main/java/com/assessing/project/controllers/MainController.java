package com.assessing.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String enter(Model model) {
        model.addAttribute("title", "Вхід");
        return "enter";
    }

}