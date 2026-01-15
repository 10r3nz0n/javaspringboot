package com.lorenzon.aluno.interfaces.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    @GetMapping({ "/", "/web/home" })
    public String home() {
        return "home";
    }
}
