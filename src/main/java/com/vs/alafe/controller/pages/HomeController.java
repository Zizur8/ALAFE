package com.vs.alafe.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
//Cambio para git dev
//oro cambio
@Controller
@RequestMapping("/index")
public class HomeController {

    @GetMapping
    public String HomePage() {
        return "home/index";
    }
}