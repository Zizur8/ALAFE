package com.vs.alafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
//Cambio para git dev
@Controller
@RequestMapping("/index")
public class HomeController {

    @GetMapping
    public String HomePage() {
        return "home/index";
    }
}